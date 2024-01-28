package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.Scenario;

import java.util.List;

public interface ScenarioService {

    /**
     *  列出所有场景 （比如：交叉口、车库）
     * @return 场景列表
     */
    List<Scenario> listAllScenarios();
}