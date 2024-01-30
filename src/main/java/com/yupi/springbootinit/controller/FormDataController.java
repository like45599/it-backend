package com.yupi.springbootinit.controller;


import com.yupi.springbootinit.service.FileStorageService;
import com.yupi.springbootinit.service.impl.FileStorageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Api(value = "File Upload Controller", tags = "File Upload")
@RestController
public class FormDataController {

    @Autowired
    private FileStorageService fileStorageService;

//    public FileStorageService fileStorageService() {
//        return new FileStorageServiceImpl();
//    }
//    @Autowired
//    public FormDataController(FileStorageService fileStorageService) {
//        this.fileStorageService = fileStorageService;
//    }

    // 允许的文件类型
    private final List<String> ALLOWED_FILE_TYPES = Collections.unmodifiableList(Arrays.asList("image/jpeg", "image/png", "application/pdf"));

    @ApiOperation(value = "Upload a file")
    @PostMapping("/file-upload")
    private ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("sessionId") String sessionId) {
        String fileName = fileStorageService.storeFile(file, sessionId);
        return ResponseEntity.ok().body("File uploaded successfully:" + fileName);
    }
}