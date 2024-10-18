package com.dream.catcher.controller;

import com.dream.catcher.dto.SpotPositionDto;
import com.dream.catcher.service.CheckRecentSpotService;
import com.dream.catcher.service.RedisService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/positions")
public class QuestProcessAPI {
    private final RedisService redisService; // RedisService 사용
    private final CheckRecentSpotService checkRecentSpotService; // CheckRecentSpotService 사용

    // Spot의 위치 데이터를 리스트에 추가하는 API
    @PostMapping("/add/{regionId}")
    public void addSpotPosition(@PathVariable Long regionId, @RequestBody SpotPositionDto spotPosition) {
        redisService.addSpotPosition(regionId, spotPosition); // RedisService 호출
    }

    // 유저의 좌표를 기반으로 근처 스팟 ID를 조회하는 API
    @GetMapping("/nearby/{regionId}/{userX}/{userY}")
    public Long getNearbySpotId(@PathVariable Long regionId, @PathVariable Double userX, @PathVariable Double userY) {
        return checkRecentSpotService.getNearbySpotId(regionId, userX, userY); // 근처 스팟 ID 반환
    }
}
