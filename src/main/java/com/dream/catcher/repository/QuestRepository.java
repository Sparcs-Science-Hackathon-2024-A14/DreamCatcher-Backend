package com.dream.catcher.repository;

import com.dream.catcher.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

    // 전체 퀘스트 반환
    @Query("select q from Quest q")
    List<Quest> getQuestList();
}
