package com.yupi.springbootinit.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yupi.springbootinit.mapper.FileInfoMapper;
import com.yupi.springbootinit.mapper.FormDataMapper;
import com.yupi.springbootinit.model.entity.FileInfo;
import com.yupi.springbootinit.model.entity.FormData;
import com.yupi.springbootinit.service.IFormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FormDataServiceImpl implements IFormDataService {

    @Autowired
    private FormDataMapper formDataMapper;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean saveFormData(FormData formData) {
        return false;
    }

    @Transactional
    @Override
    public boolean saveFormDataWithFiles(String dataJson, List<MultipartFile> files) {
        // 反序列化JSON为Map
        Map<String, String> dataMap = new HashMap<>();
        try {
            dataMap = objectMapper.readValue(dataJson, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // 保存FormData
        FormData formData = new FormData();
        formData.setData(dataJson); // 直接保存序列化的JSON字符串
        formDataMapper.insert(formData);

        // 处理文件上传
        for (MultipartFile file : files) {
            // 文件存储逻辑，这里需要实现具体的文件保存操作，并返回文件路径
            String filePath = storeFile(file);

            // 保存FileInfo
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFormDataId(formData.getId());
            fileInfo.setFilePath(filePath);
            fileInfo.setOriginalFileName(file.getOriginalFilename());
            fileInfoMapper.insert(fileInfo);
        }

        return true;
    }

    private String storeFile(MultipartFile file) {
        // 这里添加文件存储逻辑，例如保存到磁盘或云存储，并返回文件的路径或URL
        // 示例代码，实际应用中需要具体实现
        return "path/to/file";
    }

    @Override
    public FormData getFormDataById(String id) {
        return formDataMapper.selectById(id);
    }
}