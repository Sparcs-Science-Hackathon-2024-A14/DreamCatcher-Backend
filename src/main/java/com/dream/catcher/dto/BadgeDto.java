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
@Schema(description = "Badge 정보를 담은 DTO")
public class BadgeDto {
    @Schema(description = "Badge(Quest)의 ID", example = "1")
    public Long badgeId;
    @Schema(description = "Badge의 이름", example = "열기구 뱃지")
    public String badgeName;
    @Schema(description = "Badge의 설명", example = "꿈꿈나라 열기구가 하늘을 날게 도와줬어요 :D")
    public String badgeDescription;
    @Schema(description = "Badge 이미지, S3 주소")
    public String badgeImg;
}
