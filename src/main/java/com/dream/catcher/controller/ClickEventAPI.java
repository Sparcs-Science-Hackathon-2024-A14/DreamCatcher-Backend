package com.dream.catcher.controller;

import com.dream.catcher.dto.QuestPopupResponseDto;
import com.dream.catcher.dto.SpotPositionDto;
import com.dream.catcher.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/click")
public class ClickEventAPI {
    private final MapService mapService;

    @GetMapping("/nearest-spot/{regionId}/{x}/{y}")
    public QuestPopupResponseDto getNearestSpot(
            @PathVariable Long regionId, // 지역 ID
            @PathVariable Double x, // 경도
            @PathVariable Double y) { // 위도

        QuestPopupResponseDto nearestSpot = mapService.getQuestNearByMember(regionId, y, x);

        if (nearestSpot != null) {
            return nearestSpot;
        } else {
            return null; // 10m 이내의 스팟이 없을 경우 404 반환
        }
    }
}
