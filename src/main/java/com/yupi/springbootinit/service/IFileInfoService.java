package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.FileInfo;

import java.util.List;

public interface IFileInfoService {
    boolean saveFileInfo(FileInfo fileInfo);
    FileInfo getFileInfoById(Long id);
    List<FileInfo> getFileInfosByFormDataId(String formDataId);
}