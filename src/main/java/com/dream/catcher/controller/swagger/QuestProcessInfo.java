package com.dream.catcher.controller.swagger;

import com.dream.catcher.dto.QuestResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Quest Process API", description = "퀘스트 API")
public interface QuestProcessInfo {

    @Operation(summary = "퀘스트 수행을 위한 API", description = "null 이 반환되기 전까지 계속 호출하면 됩니다")
    QuestResponseDto quest(
            @Parameter(description = "사용자 ID") Long id,
            @Parameter(description = "퀘스트 ID, questPopup 호출 시 제공됩니다.")
            Long questId,
            @Parameter(description = "반환되는 nextProcessId를 입력하면 됩니다")  Long nextProcessId
    );
}
