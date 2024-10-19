package com.dream.catcher.repository;

import com.dream.catcher.domain.QuestProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestProcessRepository extends JpaRepository<QuestProcess, Long> {


    @Query("select qp from QuestProcess qp where qp.quest.id = :questId and qp.id = :nextQuestId")
    public Optional<QuestProcess> getQuestProcess(
            @Param("questId") Long questId,
            @Param("nextQuestId") Long nextQuestId
    );

    @Query("select qp from QuestProcess qp where qp.quest.id = :questId and qp.id = :previousQuestId")
    public Optional<QuestProcess> getPreviousQuestProcess(
            @Param("questId") Long questId,
            @Param("previousQuestId") Long previousQuestId
    );


}
