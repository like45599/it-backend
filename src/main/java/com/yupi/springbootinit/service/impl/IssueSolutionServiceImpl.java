package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.springbootinit.mapper.IssueSolutionMapper;
import com.yupi.springbootinit.model.dto.IssueSolutionDTO;
import com.yupi.springbootinit.model.entity.IssueSolution;
import com.yupi.springbootinit.service.IssueSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IssueSolutionServiceImpl implements IssueSolutionService {

    @Autowired
    private IssueSolutionMapper issueSolutionMapper;

    @Override
    public List<IssueSolution> findSolutionsByScenarioId(Integer scenarioId) {
        // 创建查询条件
        QueryWrapper<IssueSolution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scenario_id", scenarioId);
        // 根据场景ID查询问题及解决方案
        return issueSolutionMapper.selectList(queryWrapper);
    }

    public void saveIssueSolutionsFromDTOs(List<IssueSolutionDTO> issueSolutionDTOS) {
        issueSolutionDTOS.forEach(dto -> {
            IssueSolution issueSolution = new IssueSolution();
            issueSolution.setRootCause(dto.getRootCause());
            issueSolution.setIssueDescription(dto.getIssueDescription());
            issueSolution.setSolutionDescription(dto.getSolutionDescription());

            // 设置外键或其他需要的属性
            // issueSolution.setScenarioId(...);
            issueSolution.setScenarioId(4);

            issueSolutionMapper.insert(issueSolution);
        });
    }
}