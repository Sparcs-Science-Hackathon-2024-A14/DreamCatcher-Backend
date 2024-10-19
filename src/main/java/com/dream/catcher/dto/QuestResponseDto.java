package com.dream.catcher.dto;

import com.dream.catcher.domain.QuestType;
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
public class QuestResponseDto {

    public Long id;
    public Long nextFirstId;
    public Long nextSecondId;

    public QuestType questType;

    public String processImg;
    public String processDescription;
    public String processTTS;

    public String optionFirst;
    public String optionSecond;
}
