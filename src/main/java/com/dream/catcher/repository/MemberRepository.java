package com.dream.catcher.repository;

import com.dream.catcher.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.userName =:userName and m.age =:age")
    Optional<Member> isMemberLoginAllowed(@Param("userName") String userName, @Param("age") Long age);

}
