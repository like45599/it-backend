package com.yupi.springbootinit.controller;

import com.yupi.springbootinit.model.dto.post.SearchEsDTO;
import com.yupi.springbootinit.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ISearchService searchService;

    /**
     * 根据 rootCause 搜索问题及解决方案
     *
     * @param rootCause 搜索的根本原因关键词
     * @param scenarioId 场景ID，可选
     * @return 搜索结果列表
     */
    @GetMapping("/search")
    public ResponseEntity<List<SearchEsDTO>> searchByRootCause(
            @RequestParam("rootCause") String rootCause,
            @RequestParam(value = "scenarioId", required = false) Integer scenarioId) {

        List<SearchEsDTO> searchResults  = searchService.searchByRootCause(rootCause, scenarioId);
        return ResponseEntity.ok(searchResults);
    }
}