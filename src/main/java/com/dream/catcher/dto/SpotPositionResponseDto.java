package com.dream.catcher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@SuperBuilder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "주변 퀘스트를 확인하기 위해 map 시작 시 호출")
public class SpotPositionResponseDto {
    @Schema(description = "퀘스트 posX, posY에 대한 좌표 리스트")
    List<SpotPositionDto> spotPositionDtoList;
}
