package com.dream.catcher.controller;

import com.dream.catcher.domain.Member;
import com.dream.catcher.dto.BadgeListResponseDto;
import com.dream.catcher.dto.LoginResponseDto;
import com.dream.catcher.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("api/")
@RequiredArgsConstructor
public class BadgePageAPI {

    private final BadgeService badgeService;
    @GetMapping("badge/{id}")
    public BadgeListResponseDto getBadgeList(
            @PathVariable("id") Long id
    ){
        return BadgeListResponseDto.builder()
                .badgeDtoList(badgeService.getBadgeList(id))
                .build();
    }

}
