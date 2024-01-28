package com.yupi.springbootinit.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface DocumentService {
    String generateWordDocument(String projectInfo, String projectName, List<MultipartFile> files) throws Exception;
}