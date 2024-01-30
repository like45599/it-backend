package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.post.SearchEsDTO;
import com.yupi.springbootinit.model.entity.IssueSolution;

import java.util.List;

/**
 * 搜索服务
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public interface ISearchService extends IService<IssueSolution> {
    /**
     * 根据根本原因搜索相关问题及解决方案
     *
     * @param rootCause 根本原因关键字
     * @return 匹配的问题及解决方案列表
     */
    List<SearchEsDTO> searchByRootCause(String rootCause, Integer scenarioId);
}
