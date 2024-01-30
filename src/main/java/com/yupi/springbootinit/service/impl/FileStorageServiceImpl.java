package com.yupi.springbootinit.service.impl;

import com.yupi.springbootinit.model.entity.FileInfo;
import com.yupi.springbootinit.service.FileStorageService;
import com.yupi.springbootinit.service.IFileInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation = Paths.get("path/upload");

    @Resource
    private IFileInfoService fileInfoService;

    public FileStorageServiceImpl() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String sessionId) {
        String fileName = sessionId + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path targetLocation = this.fileStorageLocation.resolve(fileName);

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            FileInfo fileInfo = new FileInfo();
            fileInfo.setSessionId(sessionId);
            fileInfo.setOriginalFileName(file.getOriginalFilename());
            fileInfo.setFilePath(targetLocation.toString());
            fileInfo.setFileType(file.getContentType());
            fileInfo.setCreateTime(LocalDateTime.now());

            fileInfoService.saveFileInfo(fileInfo);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
