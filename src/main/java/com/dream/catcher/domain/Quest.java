package com.dream.catcher.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Quest extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "quest_id")
    private Long id;
    private String questName;
    private String badgeName;

    private Double posX;
    private Double posY;

    private Double posX;
    private Double posY;

    private String questImg;
    private String badgeImg;

    private String questDescription;
    private String badgeDescription;
}
