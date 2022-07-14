package com.example.Calc.Service;

import com.example.Calc.Repository.MemberRepository;
import com.example.Calc.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> find(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
