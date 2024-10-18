package com.dream.catcher.controller.swagger;

import com.dream.catcher.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Login Page API", description = "로그인 관련 API")
public interface LoginPageInfo {

    @Operation(summary = "사용자 로그인", description = "이름과 나이로 로그인을 수행합니다")
    LoginResponseDto getAccessLogin(
            @Parameter(description = "사용자 이름", required = true)
            String name,
            @Parameter(description = "사용자 나이", required = true)
            Long age
    );
}
