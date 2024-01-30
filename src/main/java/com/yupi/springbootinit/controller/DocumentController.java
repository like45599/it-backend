package com.yupi.springbootinit.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yupi.springbootinit.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

//    @PostMapping("/generate-word")
//    public ResponseEntity<?> generateWordDocument(
//            @RequestParam("projectInfo") String projectInfo,
//            @RequestParam("projectName") String projectName,
//            @RequestParam("files") List<MultipartFile> files) {
//        try {
//            String filePath = documentService.generateWordDocument(projectInfo, projectName, files);
//            return ResponseEntity.ok().body(filePath);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error generating document: " + e.getMessage());
//        }
//    }

    @PostMapping("/generate-word")
    public ResponseEntity<?> generateWordDocument(@RequestParam LinkedHashMap<String, String> textFields, // 使用LinkedHashMap确保顺序,
                                                  @RequestParam("selectedResults") String selectedResultsJson, // 接收查询子页面的内容
                                                  @RequestParam(value = "files", required = false) MultipartFile[] files ) {
        try {
            // 处理文本字段
            LinkedHashMap<String, Object> allParams = new LinkedHashMap<>(textFields); // 保持文本字段的顺序

            if (files == null || files.length == 0) {
                return ResponseEntity.badRequest().body("No files uploaded");
            }


            // 处理上传的文件
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String originalFileName = file.getOriginalFilename();
                    Path filePath = Files.createTempFile("upload_", originalFileName);
                    file.transferTo(filePath.toFile());
                    allParams.put("image_" + originalFileName, filePath.toString()); // 以文件名作为键，前缀以区分
                }
            }

            // 解析selectedResultsJson
            List<Map<String, String>> selectedResults = new ObjectMapper().readValue(selectedResultsJson, new TypeReference<List<Map<String, String>>>() {});
            // 将selectedResults添加到allParams中
            allParams.put("selectedResults", selectedResults);

            // 将所有参数写入临时 JSON 文件
            File tempFile = File.createTempFile("params_", ".json");
            new ObjectMapper().writeValue(tempFile, allParams);

            // 构建并执行 Python 脚本命令
            String pythonExecutablePath = "D:\\Data\\python_\\test\\venv\\Scripts\\python.exe"; // 假设 Python 安装在此路径
            String pythonScriptPath = "D:\\Data\\python_\\test\\venv\\test.py"; // Python 脚本的完整路径
            ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutablePath, pythonScriptPath, tempFile.getAbsolutePath());

            processBuilder.redirectErrorStream(true); // 将错误输出重定向到标准输出
            Process process = processBuilder.start();

            // 读取脚本的输出（包括错误输出）
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return ResponseEntity.ok().body("Document generated successfully");
            } else {
                // 在失败的响应中包含脚本的输出，以便于调试
                return ResponseEntity.badRequest().body("Failed to generate document. Script output: " + output.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error occurred: " + e.getMessage());
        }
    }
}