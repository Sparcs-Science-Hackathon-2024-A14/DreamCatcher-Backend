package com.dream.catcher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@SuperBuilder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "퀘스트 좌표 객체")
public class SpotPositionDto implements Serializable {

    @Schema(description = "퀘스트 ID, 추후 퀘스트 수행을 위해서는 저장해야 합니다", example = "1")
    private Long id; // Spot의 id
    @Schema(description = "경도 posX")
    private Double posX; // x좌표 -> 경도
    @Schema(description = "위도 posY")
    private Double posY; // y좌표 -> 위도
}
