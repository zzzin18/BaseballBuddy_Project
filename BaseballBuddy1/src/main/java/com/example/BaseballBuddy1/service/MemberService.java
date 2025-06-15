package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.member.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 회원가입
    public Member registration(Member member) {
        // 중복 체크
        if (memberRepository.findByMemberId(member.getMemberId()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        member.setRole("ROLE_USER");
        return memberRepository.save(member);
    }

    // 로그인 처리(UserDetailsService 구현)
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public Optional<Member> getMemberById(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}

