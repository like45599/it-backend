package com.yupi.springbootinit.service.impl;


import com.yupi.springbootinit.mapper.FileInfoMapper;
import com.yupi.springbootinit.model.entity.FileInfo;
import com.yupi.springbootinit.service.IFileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileInfoServiceImpl implements IFileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;
    @Override
    public void saveFileInfo(FileInfo fileInfo) {
        fileInfoMapper.insert(fileInfo);
        log.info("File info saved for file： {}", fileInfo.getOriginalFileName());
    }
}