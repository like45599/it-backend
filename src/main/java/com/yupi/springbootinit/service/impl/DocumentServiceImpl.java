package com.yupi.springbootinit.service.impl;

import com.yupi.springbootinit.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final String UPLOAD_DIR = "D:/Data/temp";

    @Override
    public String generateWordDocument(String projectInfo, String projectName, List<MultipartFile> files) throws Exception {
        // 保存文件到服务器
        List<String> filePaths = saveFiles(files);

        // 构建Python脚本命令
        String pythonCommand = buildPythonCommand(projectInfo, projectName, filePaths);

        // 执行Python脚本
        Process p = Runtime.getRuntime().exec(pythonCommand);
        int exitCode = p.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Error executing Python script.");
        }

        // 返回生成的Word文档路径
        return "项目报告.docx";
    }

    private List<String> saveFiles(List<MultipartFile> files) throws Exception {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdir();

        return files.stream().map(file -> {
            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            try {
                Files.write(filePath, file.getBytes());
                return filePath.toString();
            } catch (Exception e) {
                throw new RuntimeException("Failed to save file", e);
            }
        }).collect(Collectors.toList());
    }

    private String buildPythonCommand(String projectInfo, String projectName, List<String> filePaths) {
        String joinedFilePaths = String.join(";", filePaths);
        // 指定脚本的完整地址
        String scriptPath = "D:\\Data\\python_\\test\\venv\\test.py";
        // 构建Python命令，确保路径中的反斜杠被正确处理
        return String.format("python \"%s\" \"%s\" \"%s\" \"%s\"", scriptPath, projectInfo, projectName, joinedFilePaths);
    }
}