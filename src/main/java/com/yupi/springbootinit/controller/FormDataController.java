package com.yupi.springbootinit.controller;


import com.yupi.springbootinit.service.IFileInfoService;
import com.yupi.springbootinit.service.IFormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
public class FormDataController {

    @Autowired
    private IFormDataService formDataService;

    @Autowired
    private IFileInfoService fileInfoService;

    // 设置允许的最大文件大小（例如：5MB）
    private final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    // 允许的文件类型
    private final List<String> ALLOWED_FILE_TYPES = List.of("image/jpeg", "image/png", "application/pdf");

    @PostMapping("/formData/upload")
    public ResponseEntity<?> uploadFormData(
            @RequestParam("data") String dataJson,
            @RequestParam("files") List<MultipartFile> files) {

        for (MultipartFile file : files) {
            // 检查文件大小
            if (file.getSize() > MAX_FILE_SIZE) {
                return ResponseEntity.badRequest().body("Error: File size exceeds the maximum allowed limit.");
            }

            // 检查文件类型
            if (!ALLOWED_FILE_TYPES.contains(file.getContentType())) {
                return ResponseEntity.badRequest().body("Error: File type is not allowed.");
            }

            // 处理文件存储
            String storedFilePath = storeFile(file);
            if (storedFilePath == null) {
                return ResponseEntity.internalServerError().body("Error: File could not be stored.");
            }

            // TODO:将文件信息保存到数据库（需要实现）

        }

        // TODO:处理并保存表单数据（需要实现）



        return ResponseEntity.ok().body("FormData and files uploaded successfully.");
    }

    private String storeFile(MultipartFile file) {
        try {
            // 使用UUID生成安全的文件名，并保留原始文件扩展名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String storedFileName = UUID.randomUUID().toString() + fileExtension;

            // 指定文件存储路径，这里需要根据实际情况调整
            Path storagePath = Paths.get("path/to/storage", storedFileName);

            // 保存文件到存储路径
            Files.copy(file.getInputStream(), storagePath);

            return storagePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}