package com.dream.catcher.controller;

import com.dream.catcher.domain.Member;
import com.dream.catcher.dto.LoginResponseDto;
import com.dream.catcher.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginPageAPI {

    private final LoginService loginService;

    /**
     * 로그인 서비스
     *
     * @param name 사용자 이름
     * @param age  사용자 나이
     * @return LoginResponseDto
     */
    @GetMapping("{name}/{age}")
    public LoginResponseDto getAccessLogin(
            @PathVariable("name") String name,
            @PathVariable("age") Long age
    ) {
        Optional<Member> member = loginService.login(name, age);

        boolean isLoginAccessed = member.isPresent();
        Long id = member.map(Member::getId).orElse(null);

        return LoginResponseDto.builder()
                .isLoginAccessed(isLoginAccessed)
                .id(id)
                .build();
    }
}

