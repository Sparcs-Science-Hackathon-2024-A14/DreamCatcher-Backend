package com.dream.catcher.service;

import com.dream.catcher.dto.QuestPopupDto;
import com.dream.catcher.dto.SpotPositionDto;

import java.util.List;
import java.util.stream.Collectors;

import com.dream.catcher.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapService {
    private final RedisService redisService; // RedisService를 주입받습니다.
    private final QuestRepository questRepository;


    public QuestPopupDto getQuestNearByMember(Long regionId, Double posX, Double posY){

        Long nearByQuestId = calculateNearByQuest(regionId, posX, posY);
        return questRepository.findById(nearByQuestId)
                .map(quest -> QuestPopupDto.builder()
                        .questId(quest.getId())
                        .questName(quest.getQuestName())
                        .questImg(quest.getQuestImg())
                        .questDescription(quest.getQuestDescription())
                        .build())
                .orElseGet(() -> null);
    }

    // 특정 region의 모든 스팟과 비교하여 10m 이내의 스팟 ID를 반환하는 메서드
    public Long calculateNearByQuest(Long regionId, Double userX, Double userY) {
        List<SpotPositionDto> spots = redisService.getAllSpotPosition(regionId); // 해당 region의 스팟 리스트 가져오기

        for (SpotPositionDto spot : spots) {
            double distance = calculateDistance(userX, userY, spot.getX(), spot.getY());
            if (distance <= 10.0) { // 10m 이내의 스팟인지 확인
                return spot.getId(); // 첫 번째로 발견된 근처 스팟의 ID 반환
            }
        }

        return null; // 10m 이내의 스팟이 없을 경우 null 반환
    }
    // 유클리드 거리 계산
    // 지구가 구체
    private double calculateDistance(Double x1, Double y1, Double x2, Double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
