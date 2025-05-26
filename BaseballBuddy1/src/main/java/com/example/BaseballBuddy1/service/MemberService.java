package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.domain.member.Login;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> findMemberById(String ID) {
        return memberRepository.findById(ID);
    }
    public boolean memberCheck(String ID, String PW) {
        Member member = memberRepository.findById(ID).orElse(null);
        if (member == null) return false;
        return member.getPW().equals(PW);
    }


    @Transactional
    public void registerMember(Member member) {
        sameEmail(member.getEmail());
        sameID(member.getID());
        sameNickname(member.getNickname());
        usefulPassword(member.getPW());

        memberRepository.save(member);
    }

    private void sameEmail(String email) {
        memberRepository.findByEmail(email).ifPresent(m -> {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        });
    }
    private void sameID(String ID) {
        if (memberRepository.existsById(ID)) {
            throw new IllegalStateException("이미 사용 중인 아이디입니다.");
        }
    }
    private void sameNickname(String nickname) {
        memberRepository.findByNickname(nickname).ifPresent(m -> {
            throw new IllegalStateException("이미 사용 중인 닉네임입니다.");
        });
    }
    private void usefulPassword(String PW) {
        String pattern = "^.{6,}$";
        if (!PW.matches(pattern)) {
            throw new IllegalStateException("비밀번호는 6자 이상으로 설정해주세요.");
        }
    }
}
