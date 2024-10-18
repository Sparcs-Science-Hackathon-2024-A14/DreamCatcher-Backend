package com.dream.catcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberQuestRepository extends JpaRepository<MemberQuestRepository, Long> {

    // 사용자 퀘스트 클리어 여부
    @Query("select (count(mq) > 0) from MemberQuest mq " +
            "where mq.member.id = :memberId and mq.isCleared = true")
    public boolean isQuestExist(@Param("memberId") Long memberId);

}
