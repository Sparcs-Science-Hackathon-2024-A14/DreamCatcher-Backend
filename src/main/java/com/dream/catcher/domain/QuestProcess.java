package com.dream.catcher.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Jacksonized
@Getter
public class QuestProcess extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "quest_process_id")
    private Long id;

    private QuestType questType;

    private String processImg;
    private String processDescription;
    private String processTTS;

    // if questType is 'Branch' (분기 프로세스)
    private Long answer;
    private String optionFirst;
    private String optionSecond;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id")
    private Quest quest;
}
