package com.dream.catcher.service;

import com.dream.catcher.domain.MemberQuest;
import com.dream.catcher.domain.Quest;
import com.dream.catcher.domain.QuestProcess;
import com.dream.catcher.domain.QuestType;
import com.dream.catcher.dto.QuestResponseDto;
import com.dream.catcher.repository.MemberQuestRepository;
import com.dream.catcher.repository.MemberRepository;
import com.dream.catcher.repository.QuestProcessRepository;
import com.dream.catcher.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestService {

    private final MemberQuestRepository memberQuestRepository;
    private final QuestProcessRepository questProcessRepository;
    private final QuestRepository questRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public QuestResponseDto getNextQuest(Long id, Long questId, Long nextProcessId){

        Optional<QuestProcess> nextQuest;
        nextQuest = questProcessRepository.getQuestProcess(questId, nextProcessId);
        return nextQuest.map(this::calculateNextProcess)
                .orElseGet(()->{
                    if(!memberQuestRepository.isQuestExist(id, questId)) {
                        memberQuestRepository.save(
                                MemberQuest.builder()
                                        .quest(questRepository.findById(questId).orElseGet(() -> null))
                                        .member(memberRepository.findById(id).orElseGet(() -> null))
                                        .isCleared(true)
                                        .build()
                        );
                    }
                    return null;
                });
    }

    public QuestResponseDto calculateNextProcess(QuestProcess currentQuest)
    {

        QuestType currentQuestType = currentQuest.getQuestType();
        if(currentQuestType.equals(QuestType.PROCESS)){


            return QuestResponseDto.builder()
                    .id(currentQuest.getId())
                    .nextFirstId(currentQuest.getId() + 1L)
                    .isNextBranch(questProcessRepository.findById(currentQuest.getId() + 1L).get().getQuestType().equals(QuestType.BRANCH))
                    .processImg(currentQuest.getProcessImg())
                    .processTTS(currentQuest.getProcessTTS())
                    .processDescription(currentQuest.getProcessDescription())
                    .optionFirst(currentQuest.getOptionFirst())
                    .optionSecond(currentQuest.getOptionSecond())
                    .build();

        }else if(currentQuestType.equals(QuestType.BRANCH)){  // QuestType.BRANCH

            Long answer = currentQuest.getAnswer();

            return QuestResponseDto.builder()
                    .id(currentQuest.getId())
                    .nextFirstId(currentQuest.getId() + (answer.equals(1L) ? 1L : 2L))
                    .nextSecondId(currentQuest.getId() + (answer.equals(2L) ? 1L : 2L))
                    .isCurrentBranch(currentQuest.getQuestType().equals(QuestType.BRANCH))
                    .processImg(currentQuest.getProcessImg())
                    .processTTS(currentQuest.getProcessTTS())
                    .processDescription(currentQuest.getProcessDescription())
                    .optionFirst(currentQuest.getOptionFirst())
                    .optionSecond(currentQuest.getOptionSecond())
                    .build();
        }else{   //QuestType.Finish
            QuestProcess previousQuest;
            previousQuest = questProcessRepository.getPreviousQuestProcess(currentQuest.getQuest().getId(), currentQuest.getId() -1)
                    .get();

            QuestType previousQuestType = previousQuest.getQuestType();

            return QuestResponseDto.builder()
                    .id(currentQuest.getId())
                    .nextFirstId(currentQuest.getId() + (previousQuestType.equals(QuestType.FINISH) ? 1L : 2L))
                    .processImg(currentQuest.getProcessImg())
                    .processTTS(currentQuest.getProcessTTS())
                    .processDescription(currentQuest.getProcessDescription())
                    .optionFirst(currentQuest.getOptionFirst())
                    .optionSecond(currentQuest.getOptionSecond())
                    .build();
        }
    }


    public QuestType getQuestType(Long branchId){

        if(branchId.equals(0L))
            return QuestType.PROCESS;
        else
            return QuestType.BRANCH;
    }
}
