package com.yupi.springbootinit.service.impl;


import com.yupi.springbootinit.mapper.FileInfoMapper;
import com.yupi.springbootinit.model.entity.FileInfo;
import com.yupi.springbootinit.service.IFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileInfoServiceImpl implements IFileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public boolean saveFileInfo(FileInfo fileInfo) {
        return fileInfoMapper.insert(fileInfo) > 0;
    }

    @Override
    public FileInfo getFileInfoById(Long id) {
        return fileInfoMapper.selectById(id);
    }

    @Override
    public List<FileInfo> getFileInfosByFormDataId(String formDataId) {
        return fileInfoMapper.selectByFormDataId(formDataId);
    }
}