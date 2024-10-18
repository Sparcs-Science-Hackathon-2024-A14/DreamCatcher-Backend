package com.dream.catcher.repository;
import com.dream.catcher.domain.MemberQuest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberQuestRepository extends JpaRepository<MemberQuest, Long> {

    // 사용자 퀘스트 클리어 여부
    @Query("select (count(mq) > 0) from MemberQuest mq " +
            "where mq.member.id = :memberId and mq.isCleared = true")
    public boolean isQuestExist(@Param("memberId") Long memberId);

    // 사용자 뱃지 획득 내역
    @EntityGraph(attributePaths = {"quest"})
    @Query("select mq from MemberQuest mq where mq.member.id =:id")
    public List<MemberQuest> memberQuestList(@Param("id") Long id);

}
