package com.yupi.springbootinit.controller;

import com.yupi.springbootinit.model.dto.IssueSolutionDTO;
import com.yupi.springbootinit.model.entity.IssueSolution;
import com.yupi.springbootinit.service.IssueSolutionService;
import com.yupi.springbootinit.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/issueSolutions")
@Slf4j
public class IssueSolutionController {

    @Autowired
    private IssueSolutionService issueSolutionService;

    @GetMapping("/list/{scenarioId}")
    public List<IssueSolution> findSolutionsByScenarioId(@PathVariable Integer scenarioId) {
        return issueSolutionService.findSolutionsByScenarioId(scenarioId);
    }

    /**
     *  使用EasyExcel导入数据
     * @param file
     * @return
     */
    @PostMapping("/importIssueSolutions")
    public ResponseEntity<String> importIssueSolutions(@RequestParam("file") MultipartFile file) {
        try {
            List<IssueSolutionDTO> dtos = ExcelUtils.excelToIssueSolutionDTOList(file);
            issueSolutionService.saveIssueSolutionsFromDTOs(dtos);
            return ResponseEntity.ok("导入成功");
        } catch (IOException e) {
            log.error("导入失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("导入失败: " + e.getMessage());
        }
    }

}