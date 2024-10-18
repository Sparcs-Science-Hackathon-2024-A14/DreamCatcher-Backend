package com.dream.catcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String healthCheck() {
        return "OK"; // 간단한 헬스 체크 응답
    }
}
