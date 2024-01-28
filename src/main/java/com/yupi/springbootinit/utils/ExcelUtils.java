package com.yupi.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.yupi.springbootinit.model.dto.IssueSolutionDTO;
import com.yupi.springbootinit.model.entity.IssueSolution;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel 相关工具类
 */
@Slf4j
public class ExcelUtils {

    // 修改方法名称和返回类型
    public static List<IssueSolutionDTO> excelToIssueSolutionDTOList(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }

        List<IssueSolutionDTO> issueSolutionDTOList = new ArrayList<>();
        EasyExcel.read(multipartFile.getInputStream(), IssueSolutionDTO.class, new AnalysisEventListener<IssueSolutionDTO>() {
            @Override
            public void invoke(IssueSolutionDTO dto, AnalysisContext context) {
                issueSolutionDTOList.add(dto);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 所有数据解析完成后的操作
            }
        }).sheet().doRead();

        return issueSolutionDTOList;
    }
}