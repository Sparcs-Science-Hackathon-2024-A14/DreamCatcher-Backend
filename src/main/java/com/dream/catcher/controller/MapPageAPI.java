package com.dream.catcher.controller;

import com.dream.catcher.controller.swagger.MapPageInfo;
import com.dream.catcher.dto.QuestPopupResponseDto;
import com.dream.catcher.dto.SpotPositionDto;
import com.dream.catcher.dto.SpotPositionResponseDto;
import com.dream.catcher.service.MapService;
import com.dream.catcher.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/map")
public class MapPageAPI implements MapPageInfo {
    private final RedisService redisService; // RedisService 사용
    private final MapService mapService; // CheckRecentSpotService 사용

    // Spot의 위치 데이터를 리스트에 추가하는 API
    @PostMapping("/add/{regionId}")
    public void addSpotPosition(@PathVariable Long regionId, @RequestBody SpotPositionDto spotPosition) {
        redisService.addSpotPosition(regionId, spotPosition); // RedisService 호출
    }


    @GetMapping("/spot/{regionId}")
    public SpotPositionResponseDto getSpot(@PathVariable("regionId") Long regionId){
        return mapService.getRegionSpotPosition(regionId);
    }


    // 유저의 좌표를 기반으로 근처 스팟 ID를 조회하는 API
    @GetMapping("/nearby/{regionId}/{userX}/{userY}")
    public QuestPopupResponseDto getNearbySpotId(@PathVariable Long regionId, @PathVariable Double userX, @PathVariable Double userY) {
        return mapService.getQuestNearByMember(regionId, userY, userX); // 근처 스팟 ID 반환
    }
}
