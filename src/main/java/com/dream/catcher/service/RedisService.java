package com.dream.catcher.service;

import com.dream.catcher.dto.SpotPositionDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, SpotPositionDto> redisTemplate;

    // regionId에 해당해서 1.spotId 2.해당하는 x,y값이 들어있는 spotPositionDto 내용을 저장
    public void addSpotPosition(Long regionId, SpotPositionDto spotPosition) {
        // regionId를 String으로 변환하여 리스트의 오른쪽에 추가
        redisTemplate.opsForList().rightPush(regionId.toString(), spotPosition);
    }

    // 리스트에 해당하는 정보를 가져온다.
// 단 regionId는 대전에 존재하는 각 지역에 대해서로 한정한다.
// 이로써 찾아올 때 regionId에 해당하는 모든 스팟에 대하여 가져올 수 있다.
    public List<SpotPositionDto> getAllSpotPosition(Long regionId) {
        // regionId를 String으로 변환하여 리스트의 모든 요소를 가져옴
        return redisTemplate.opsForList().range(regionId.toString(), 0, -1);
    }

}
