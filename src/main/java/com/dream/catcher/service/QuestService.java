package com.dream.catcher.service;

import com.dream.catcher.domain.QuestProcess;
import com.dream.catcher.domain.QuestType;
import com.dream.catcher.dto.QuestResponseDto;
import com.dream.catcher.repository.QuestProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestService {

    private final QuestProcessRepository questProcessRepository;

    public QuestResponseDto getNextQuest(Long questId, Long nextProcessId){

        Optional<QuestProcess> nextQuest;
        nextQuest = questProcessRepository.getQuestProcess(questId, nextProcessId);
        return nextQuest.map(this::calculateNextProcess).orElse(null);
    }

    public QuestResponseDto calculateNextProcess(QuestProcess nextQuest)
    {
        QuestType nextQuestType = nextQuest.getQuestType();
        if(nextQuestType.equals(QuestType.PROCESS)){

            return QuestResponseDto.builder()
                    .id(nextQuest.getId())
                    .nextFirstId(nextQuest.getId() + 1L)
                    .processImg(nextQuest.getProcessImg())
                    .processTTS(nextQuest.getProcessTTS())
                    .processDescription(nextQuest.getProcessDescription())
                    .optionFirst(nextQuest.getOptionFirst())
                    .optionSecond(nextQuest.getOptionSecond())
                    .build();

        }else if(nextQuestType.equals(QuestType.BRANCH)){  // QuestType.BRANCH

            Long answer = nextQuest.getAnswer();

            return QuestResponseDto.builder()
                    .id(nextQuest.getId())
                    .nextFirstId(nextQuest.getId() + (answer.equals(1L) ? 1L : 2L))
                    .nextSecondId(nextQuest.getId() + (answer.equals(2L) ? 2L : 1L))
                    .processImg(nextQuest.getProcessImg())
                    .processTTS(nextQuest.getProcessTTS())
                    .processDescription(nextQuest.getProcessDescription())
                    .optionFirst(nextQuest.getOptionFirst())
                    .optionSecond(nextQuest.getOptionSecond())
                    .build();
        }
        else{   //QuestType.Finish
            QuestProcess previousQuest;
            previousQuest = questProcessRepository.getPreviousQuestProcess(nextQuest.getQuest().getId(), nextQuest.getId())
                    .get();

            QuestType previousQuestType = previousQuest.getQuestType();

            return QuestResponseDto.builder()
                    .id(nextQuest.getId())
                    .nextFirstId(nextQuest.getId() + (previousQuestType.equals(QuestType.FINISH) ? 1L : 2L))
                    .processImg(nextQuest.getProcessImg())
                    .processTTS(nextQuest.getProcessTTS())
                    .processDescription(nextQuest.getProcessDescription())
                    .optionFirst(nextQuest.getOptionFirst())
                    .optionSecond(nextQuest.getOptionSecond())
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
