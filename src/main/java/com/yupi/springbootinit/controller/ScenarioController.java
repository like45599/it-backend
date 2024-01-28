package com.yupi.springbootinit.controller;

import com.yupi.springbootinit.model.entity.Scenario;
import com.yupi.springbootinit.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/scenarios")
public class ScenarioController {

    @Autowired
    private ScenarioService scenarioService;

    @GetMapping("/list")
    public List<Scenario> listAllScenarios() {
        return scenarioService.listAllScenarios();
    }
}
