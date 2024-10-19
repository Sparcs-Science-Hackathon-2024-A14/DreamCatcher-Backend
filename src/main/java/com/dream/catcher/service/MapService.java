package com.dream.catcher.service;

import com.dream.catcher.domain.Quest;
import com.dream.catcher.dto.QuestPopupResponseDto;
import com.dream.catcher.dto.SpotPositionDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.dream.catcher.dto.SpotPositionResponseDto;
import com.dream.catcher.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapService {
    private final RedisService redisService; // RedisService를 주입받습니다.
    private final QuestRepository questRepository;
    private static final int R = 6371; // 지구의 반지름 (킬로미터)

    public SpotPositionResponseDto getRegionSpotPosition(Long regionId) {
        if (regionId == null) {
            return null; // regionId가 null일 경우 null 반환
        }

        List<SpotPositionDto> spotPositionDtoList = questRepository.getQuestList()
                .stream()
                .filter(Objects::nonNull)  // Null 값 필터링
                .map(this::convertToSpotPositionDto)  // 메서드 분리로 가독성 향상
                .collect(Collectors.toList());

        return SpotPositionResponseDto.builder()
                .spotPositionDtoList(spotPositionDtoList)
                .build();
    }

    // Dto 변환 메서드 분리
    private SpotPositionDto convertToSpotPositionDto(Quest quest) {
        if (quest == null) {
            return null; // quest가 null일 경우 null 반환
        }
        return SpotPositionDto.builder()
                .id(quest.getId())
                .posX(quest.getPosX())
                .posY(quest.getPosY())
                .build();
    }

    public QuestPopupResponseDto getQuestNearByMember(Long regionId, Double posX, Double posY) {
        if (regionId == null || posX == null || posY == null) {
            return null; // 입력 값이 null일 경우 null 반환
        }

        Long nearByQuestId = calculateNearByQuest(regionId, posX, posY);
        if (nearByQuestId == null) {
            return null; // 근처 퀘스트가 없는 경우 null 반환
        }

        return questRepository.findById(nearByQuestId)
                .map(quest -> QuestPopupResponseDto.builder()
                        .questId(quest.getId())
                        .questName(quest.getQuestName())
                        .questImg(quest.getQuestImg())
                        .questDescription(quest.getQuestDescription())
                        .build())
                .orElse(null); // ID로 찾는 퀘스트가 없을 경우 null 반환
    }

    // 특정 region의 모든 스팟과 비교하여 10m 이내의 스팟 ID를 반환하는 메서드
    public Long calculateNearByQuest(Long regionId, Double userX, Double userY) {
        if (regionId == null || userX == null || userY == null) {
            return null; // 입력 값이 null일 경우 null 반환
        }

        List<SpotPositionDto> spots = redisService.getAllSpotPosition(regionId); // 해당 region의 스팟 리스트 가져오기

        if (spots == null || spots.isEmpty()) {
            return null; // 스팟 리스트가 비어있으면 null 반환
        }

        for (SpotPositionDto spot : spots) {
            double distance = calculateDistance(userX, userY, spot.getPosX(), spot.getPosY());
            if (distance <= 300.0) { // 300m 이내의 스팟인지 확인
                return spot.getId(); // 첫 번째로 발견된 근처 스팟의 ID 반환
            }
        }
        return null; // 10m 이내의 스팟이 없을 경우 null 반환
    }

    // Haversine 공식 사용 예시
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 위도와 경도를 라디안으로 변환
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Haversine 공식을 사용하여 거리 계산
        double dlon = lon2Rad - lon1Rad;
        double dlat = lat2Rad - lat1Rad;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 거리를 미터로 변환
        double distance = R * c * 1000; // 킬로미터를 미터로 변환

        // 소수점 6자리까지 반올림
        return Math.round(distance * 1000000.0) / 1000000.0;
    }
}
