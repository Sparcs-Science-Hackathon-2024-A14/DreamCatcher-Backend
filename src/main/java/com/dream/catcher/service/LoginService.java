package com.dream.catcher.service;

import com.dream.catcher.domain.Member;
import com.dream.catcher.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final MemberRepository memberRepository;

    public Optional<Member> login(String name, Long age){
        return memberRepository.isMemberLoginAllowed(name, age);
    }
}
