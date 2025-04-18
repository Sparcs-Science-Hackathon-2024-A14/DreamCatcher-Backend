package com.dream.catcher.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration    // 스프링 실행시 설정파일 읽어드리기 위한 어노테이션
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addServersItem(new Server().url("/").description("https설정"))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("DreamCathcer")
                .description("Sparcs 해커톤 2024 - API 명세서")
                .version("1.0.0");
    }
}