package com.yupi.springbootinit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SessionController {

    @GetMapping("/session/init")
    public String initSession() {
        // 生成新的UUID
        String seesionId = UUID.randomUUID().toString();

        // TODO: 根据需要執行其他初始化邏輯，例如在数据库中创建会话记录

        // 返回生成的UUID
        return seesionId;
    }
}
