package com.dream.catcher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "주기적으로 보내는 위치 값에 대한 반환")
public class MapSpotResponseDto {
    @Schema(description = "주변에 퀘스트가 있는지 여부", example = "true")
    boolean isSpotNearby;
    @Schema(description = "주변에 퀘스트가 있을 경우, 상단에 떠야 할 팝업에 대한 데이터")
    QuestPopupResponseDto questPopupDto;
}
