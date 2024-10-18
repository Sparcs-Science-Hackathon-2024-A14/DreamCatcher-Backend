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
public class MemberQuest extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "member_quest_id")
    private Long id;

    private boolean isCleared;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id")
    private Quest quest;
}
