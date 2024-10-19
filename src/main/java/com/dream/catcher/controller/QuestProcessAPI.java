package com.dream.catcher.controller;

import com.dream.catcher.dto.QuestResponseDto;
import com.dream.catcher.dto.SpotPositionDto;
import com.dream.catcher.service.MapService;
import com.dream.catcher.service.QuestService;
import com.dream.catcher.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quest")
public class QuestProcessAPI {

    private final QuestService questService;

    @GetMapping("{quest_id}/{next_process_id}")
    public QuestResponseDto quest(
            @PathVariable("quest_id") Long questId,
            @PathVariable("next_process_id") Long nextProcessId
    ){
        return questService.getNextQuest(questId, nextProcessId);
    }


}
