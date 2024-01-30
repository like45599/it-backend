package com.yupi.springbootinit.esdao;

import com.yupi.springbootinit.model.dto.post.PostEsDTO;
import com.yupi.springbootinit.model.dto.post.SearchEsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;


@SpringBootTest
public class SearchEsDaoTest {
    @Resource
    private SearchEsDao searchEsDao;


    @Test
    void testAdd() {
        SearchEsDTO searchEsDTO = new SearchEsDTO();
        searchEsDTO.setId(989L);
//        searchEsDTO.setScenario_id(1);
        searchEsDTO.setRoot_cause("研究范围内存在新建交叉口");
        searchEsDTO.setIssue_description("新建道路网规划中，若规划干路交叉口存在超过4条进口道的多路交叉口、错位交叉口、畸形交叉口；");
        searchEsDTO.setSolution_description("新建道路网规划中，规划干路交叉口减少至4条进口道的多路交叉口、错位交叉口、畸形交叉口；");
        System.out.println(searchEsDTO.getId());
    }


    @Test
    void testFindById() {
        Optional<SearchEsDTO> searchEsDTO = searchEsDao.findById(989L);
        System.out.println(searchEsDTO);
    }
}