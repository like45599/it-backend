package com.yupi.springbootinit.controller;

import com.yupi.springbootinit.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/generate-word")
    public ResponseEntity<?> generateWordDocument(
            @RequestParam("projectInfo") String projectInfo,
            @RequestParam("projectName") String projectName,
            @RequestParam("files") List<MultipartFile> files) {
        try {
            String filePath = documentService.generateWordDocument(projectInfo, projectName, files);
            return ResponseEntity.ok().body(filePath);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error generating document: " + e.getMessage());
        }
    }
}