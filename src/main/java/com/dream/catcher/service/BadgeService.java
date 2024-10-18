package com.dream.catcher.service;

import com.dream.catcher.domain.MemberQuest;
import com.dream.catcher.dto.BadgeDto;
import com.dream.catcher.repository.MemberQuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BadgeService {

    private final MemberQuestRepository memberQuestRepository;

    public List<BadgeDto> getBadgeList(Long id) {
        return memberQuestRepository.memberQuestList(id)
                .stream()
                .filter(MemberQuest::isCleared)
                .map(memberQuest -> BadgeDto.builder()
                        .badgeId(memberQuest.getQuest().getId())
                        .badgeName(memberQuest.getQuest().getBadgeName())
                        .badgeImg(memberQuest.getQuest().getQuestImg())
                        .badgeDescription(memberQuest.getQuest().getBadgeDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
