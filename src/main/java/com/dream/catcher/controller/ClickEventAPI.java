package com.dream.catcher.controller;

import com.dream.catcher.dto.SpotPositionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/click")
public class ClickEventAPI {

    @PostMapping("/{spotId}")
    public SpotPositionDto getSpotInfo(@PathVariable Long spotId){

    }

}
