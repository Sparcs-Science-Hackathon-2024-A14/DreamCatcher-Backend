package com.dream.catcher.controller.swagger;

import com.dream.catcher.dto.QuestPopupResponseDto;
import com.dream.catcher.dto.SpotPositionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Map Page API", description = "지도 관련 API")
public interface MapPageInfo {

    @Operation(summary = "(테스트용) 스팟 위치 추가", description = "스팟(위도, 경도)에 대한 데이터를 캐시에 입력합니다")
    void addSpotPosition(
            @Parameter(description = "지역 ID", required = true) Long regionId,
            @Parameter(description = "스팟 위치 데이터", required = true) SpotPositionDto spotPosition
    );

    @Operation(summary = "사용자 주변 스팟 추적",
            description = "유저의 좌표를 기반으로 10M이내 스팟을 조회합니다. 10M 이내 스팟이 존재하지 않을 경우 빈 DTO를 반환합니다.")
    QuestPopupResponseDto getNearbySpotId(
            @Parameter(description = "지역 ID(1로 고정할 것)", required = true) Long regionId,
            @Parameter(description = "유저 X 좌표", required = true) Double userX,
            @Parameter(description = "유저 Y 좌표", required = true) Double userY
    );
}

