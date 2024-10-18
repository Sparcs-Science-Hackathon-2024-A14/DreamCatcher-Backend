package com.dream.catcher.controller.swagger;


import com.dream.catcher.dto.BadgeListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Badge Page API", description = "뱃지 관련 API")
public interface BadgePageInfo {

    @Operation(summary = "사용자 수집 뱃지 조회", description = "사용자(ID)가 수집한 뱃지를 반환합니다.")
    BadgeListResponseDto getBadgeList(
            @Parameter(
                    description = "사용자 ID",
                    required = true
            ) Long id
    );
}
