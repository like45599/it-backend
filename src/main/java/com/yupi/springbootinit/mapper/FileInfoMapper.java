package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.springbootinit.model.entity.FileInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FileInfoMapper extends BaseMapper<FileInfo> {
    // MyBatis Plus提供了基本的CRUD操作

    @Select("SELECT * FROM file_info WHERE form_data_id = #{formDataId}")
    List<FileInfo> selectByFormDataId(String formDataId);
}
