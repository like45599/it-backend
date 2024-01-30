package com.yupi.springbootinit.job.once;

import cn.hutool.core.collection.CollUtil;
import com.yupi.springbootinit.esdao.SearchEsDao;
import com.yupi.springbootinit.model.dto.post.SearchEsDTO;
import com.yupi.springbootinit.model.entity.IssueSolution;
import com.yupi.springbootinit.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全量同步搜索到 es
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
// todo 取消注释开启任务
@Component
@Slf4j
public class FullSyncSearchToEs implements CommandLineRunner {

    @Resource
    private ISearchService searchService;

    @Resource
    private SearchEsDao searchEsDao;

    @Override
    public void run(String... args) {
        List<IssueSolution> searchList = searchService.list();

        if (CollUtil.isEmpty(searchList)) {
            return;
        }

        List<SearchEsDTO> searchEsDTOList = searchList.stream().map(SearchEsDTO::objToDto).collect(Collectors.toList());
        final int pageSize = 500;
        int total = searchEsDTOList.size();
        log.info("FullSyncPostToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            searchEsDao.saveAll(searchEsDTOList.subList(i, end));
        }
        log.info("FullSyncPostToEs end, total {}", total);
    }
}
