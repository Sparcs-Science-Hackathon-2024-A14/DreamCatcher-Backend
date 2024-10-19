package com.dream.catcher.controller;

import com.dream.catcher.controller.swagger.LoginPageInfo;
import com.dream.catcher.domain.Member;
import com.dream.catcher.dto.LoginResponseDto;
import com.dream.catcher.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginPageAPI implements LoginPageInfo {

    private final LoginService loginService;

    /**
     * 로그인 서비스
     *
     * @param name 사용자 이름
     * @param age  사용자 나이
     * @return LoginResponseDto
     */
    @PostMapping("{name}/{age}")
    public LoginResponseDto getAccessLogin(
            @PathVariable("name") String name,
            @PathVariable("age") Long age
    ) {
        // 로그인 시도
        Optional<Member> optionalMember = loginService.login(name, age);

        // 멤버가 존재하지 않을 경우, 새로 등록
        Member member = optionalMember.orElseGet(() -> loginService.register(name, age));

        // 멤버 ID를 가져와 DTO 생성
        return LoginResponseDto.builder()
                .id(member.getId())
                .build();
    }
}

