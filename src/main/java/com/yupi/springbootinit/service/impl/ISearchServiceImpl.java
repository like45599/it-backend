package com.yupi.springbootinit.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.mapper.IssueSolutionMapper;
import com.yupi.springbootinit.model.dto.post.SearchEsDTO;
import com.yupi.springbootinit.model.entity.IssueSolution;
import com.yupi.springbootinit.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子服务实现
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Service
@Slf4j
public class ISearchServiceImpl extends ServiceImpl<IssueSolutionMapper, IssueSolution> implements ISearchService {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<SearchEsDTO> searchByRootCause(String rootCause, Integer scenarioId) {
        // 构建查询
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 根据 scenarioId 是否特定值来构建查询条件
        if (scenarioId != null && scenarioId != -1) { // 假设 -1 表示“综合查询”
            queryBuilder.withQuery(
                    QueryBuilders.boolQuery()
                            .must(QueryBuilders.matchQuery("root_cause", rootCause))
                            .filter(QueryBuilders.termQuery("scenario_id", scenarioId))
            );
        } else {
            queryBuilder.withQuery(QueryBuilders.matchQuery("root_cause", rootCause));
        }

        queryBuilder.withPageable(PageRequest.of(0, 5)); // 限制返回的结果数量为 5 条

        // 执行搜索
        SearchHits<SearchEsDTO> searchHits = elasticsearchRestTemplate.search(queryBuilder.build(), SearchEsDTO.class);

        // 将搜索结果转换为 DTO 列表并返回
        return searchHits.getSearchHits().stream()
                .map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchEsDTO> searchAllByRootCause(String rootCause) {
        // 构建查询
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 单字段查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("root_cause", rootCause));

        queryBuilder.withPageable(PageRequest.of(0, 10)); // 限制返回的结果数量，这里以10为例

        // 执行搜索
        SearchHits<SearchEsDTO> searchHits = elasticsearchRestTemplate.search(queryBuilder.build(), SearchEsDTO.class);

        // 将搜索结果转换为 DTO 列表并返回
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}




