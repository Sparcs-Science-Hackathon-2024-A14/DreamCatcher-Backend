package com.dream.catcher.repository;

import com.dream.catcher.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select count(m) > 0 from Member m where m.userName =:userName and m.age =:age")
    boolean isMemberLoginAllowed(@Param("userName") String userName, @Param("age") Long age);

}
