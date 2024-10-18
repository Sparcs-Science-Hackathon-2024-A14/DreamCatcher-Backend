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
@Schema(description = "퀘스트 스팟 주변에 있거나, 퀘스트 스팟을 눌렀을 경우 반환되는 값")
public class QuestPopupDto {

    @Schema(description = "퀘스트 ID", example = "1")
    Long questId;
    @Schema(description = "퀘스트 이름", example = "열기구를 띄워주세요 @_@")
    String questName;
    @Schema(description = "퀘스트 이미지, S3 주소")
    String questImg;
    @Schema(description = "퀘스트 설명", example = "뭔가 잘못된 것 같아요... \n모두의 염원을 담은 열기구를 하늘 높이 띄워주세요 @_@")
    String questDescription;
}
