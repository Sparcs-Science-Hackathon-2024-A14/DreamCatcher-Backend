package com.dream.catcher.dto;

import com.dream.catcher.domain.QuestType;
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
@Schema(description = "퀘스트 객체, 퀘스트 창에 띄워야 할 데이터와 다음에 호출해야 할 번호가 저장되어있다.")
public class QuestResponseDto {

    @Schema(description = "현재 퀘스트 단계 번호")
    public Long id;
    @Schema(description = "(중요)다음에 호출해야 할 퀘스트 번호, " +
            "questType 이 branch 일 경우, 첫번째 버튼을 누를 경우 nextFirstId 두번째 버튼을 누를 경우 nextSecondId를 PathVariable에 입력할 것" +
            "그외 모든 경우는 nextFirstId 입력할 것")
    public Long nextFirstId;
    @Schema(description = "(중요)다음에 호출해야 할 퀘스트 번호, " +
            "questType 이 branch 일 경우, 첫번째 버튼을 누를 경우 nextFirstId 두번째 버튼을 누를 경우 nextSecondId를 PathVariable에 입력할 것" +
            "그외 모든 경우는 nextFirstId 입력할 것")
    public Long nextSecondId;

    @Schema(description = "(중요)현재 퀘스트 페이지의 종류 " +
            "'process' : 단순 퀘스트 페이지, 'branch' 선택지가 있는 퀘스트 페이지, 'finish' 선택지를 선택했을 경우 나오는 결과 페이지")
    public QuestType questType;

    @Schema(description = "(중요)현재 퀘스트 페이지의 종류")
    public boolean isNextBranch;

    @Schema(description = "퀘스트 페이지에서 사용할 이미지")
    public String processImg;
    @Schema(description = "퀘스트 페이지에서 사용할 설명")
    public String processDescription;
    @Schema(description = "퀘스트 페이지에서 사용할 음성 파일")
    public String processTTS;

    @Schema(description = "첫 번째 선택지에 대한 텍스트")
    public String optionFirst;
    @Schema(description = "두 번째 선택지에 대한 텍스트")
    public String optionSecond;

    @Schema(description = "첫 번째 힌트에 대한 텍스트")
    public String firstHint;
    @Schema(description = "두 번째 힌트에 대한 텍스트")
    public String secondHint;
}
