package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.dto.IssueSolutionDTO;
import com.yupi.springbootinit.model.entity.IssueSolution;

import java.util.List;

public interface IssueSolutionService {


    /**
     *  根据场景ID查找对应的问题和解决方案。
     * @param scenarioId 场景ID
     * @return 该场景下的问题和解决啊方案列表
     */
    List<IssueSolution> findSolutionsByScenarioId(Integer scenarioId);

    public void saveIssueSolutionsFromDTOs(List<IssueSolutionDTO> issueSolutionDTOS);
}