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
@Schema(description = "로그인 결과를 담은 DTO")
public class LoginResponseDto {

    @Schema(description = "사용자 ID, 로그인 성공 시 반환", example = "1")
    public Long id;
}
