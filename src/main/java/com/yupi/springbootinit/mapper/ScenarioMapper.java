package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.springbootinit.model.entity.Scenario;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScenarioMapper extends BaseMapper<Scenario> {

}