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

    @GetMapping("/nearest-spot")
    public ResponseEntity<QuestPopupResponseDto> getNearestSpot(
            @RequestParam Double x, // 경도
            @RequestParam Double y, // 위도
            @RequestParam Long regionId) {

        QuestPopupResponseDto nearestSpot = mapService.getQuestNearByMember(regionId, x, y);

        if (nearestSpot != null) {
            return ResponseEntity.ok(nearestSpot);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 10m 이내의 스팟이 없을 경우 404 반환
        }
    }
}
