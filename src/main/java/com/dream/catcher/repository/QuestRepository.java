package com.dream.catcher.repository;

import com.dream.catcher.domain.Quest;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {


    @Query("select q from Quest q where q.posX = :posX and q.posY = :posY")
    Optional<Quest> getQuestByPosition(@Param("posX") Double posX, @Param("posY") Double posY);

    // 전체 퀘스트 반환
    @Query("select q from Quest q")
    List<Quest> getQuestList();
}
