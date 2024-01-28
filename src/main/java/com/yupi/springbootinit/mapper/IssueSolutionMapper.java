package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.springbootinit.model.entity.IssueSolution;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IssueSolutionMapper extends BaseMapper<IssueSolution> {
    // MyBatis-Plus 会提供默认实现，无需额外定义方法
}