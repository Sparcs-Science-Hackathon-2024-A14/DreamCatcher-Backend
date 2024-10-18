package com.dream.catcher.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SpotPositionDto {
    private Long id; // Spot의 id
    private Double x; // x좌표 -> 경도
    private Double y; // y좌표 -> 위도
}
