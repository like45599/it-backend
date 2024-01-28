package com.yupi.springbootinit.service;


import com.yupi.springbootinit.model.entity.FormData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFormDataService {
    boolean saveFormData(FormData formData);

    boolean saveFormDataWithFiles(String dataJson, List<MultipartFile> files);

    FormData getFormDataById(String id);
}