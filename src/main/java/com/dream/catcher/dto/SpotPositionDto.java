package com.dream.catcher.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpotPositionDto implements Serializable {
    private Long id; // Spot의 id
    private Double posX; // x좌표 -> 경도
    private Double posY; // y좌표 -> 위도
}
