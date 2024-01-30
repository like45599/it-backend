package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileInfoService {
    void saveFileInfo(FileInfo fileInfo);
//    String storeFile(MultipartFile file, String sessionId);
//    FileInfo getFileInfoById(Long id);
//    List<FileInfo> getFileInfosByFormDataId(String formDataId);
}