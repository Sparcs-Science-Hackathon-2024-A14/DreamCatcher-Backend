package com.dream.catcher.repository;

import com.dream.catcher.domain.QuestProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestProcessRepository extends JpaRepository<QuestProcess, Long> {
}
