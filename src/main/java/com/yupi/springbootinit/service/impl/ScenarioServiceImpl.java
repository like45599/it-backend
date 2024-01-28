package com.yupi.springbootinit.service.impl;

import com.yupi.springbootinit.mapper.ScenarioMapper;
import com.yupi.springbootinit.model.entity.Scenario;
import com.yupi.springbootinit.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScenarioServiceImpl implements ScenarioService {

    @Autowired
    private ScenarioMapper scenarioMapper;

    @Override
    public List<Scenario> listAllScenarios() {
        // 查询并返回所有场景记录
        return scenarioMapper.selectList(null);
    }
}