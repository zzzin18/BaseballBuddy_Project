package com.example.BaseballBuddy1.domain.member;

import jakarta.persistence.Entity;

import java.util.Optional;
import java.util.regex.Pattern;

@Entity
public class Registeration {
    private String ID;
    private String PW;
    private String nickname;
    private String email;

    public Registeration(String ID, String PW, String nickname, String email) {
        this.ID = ID;
        this.PW = PW;
        this.nickname = nickname;
        this.email = email;
    }

    public void hasMember(String email, MemberRepository memberRepository) {
        Optional<Member> existing = memberRepository.findByEmail(email);
        if (existing.isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }
    public boolean hasID(String ID, MemberRepository memberRepository) {
        return memberRepository.findById(ID).isPresent();
    }
    public boolean hasNickname(String nickname, MemberRepository memberRepository) {
        return memberRepository.findByNickname(nickname).isPresent();
    }
    public boolean checkPW(String PW) {
        String pattern = "^.{6,}$";
        return Pattern.matches(pattern, PW);
    }

    public Member Registration() {
        return new Member(ID, PW, nickname, email);
    }
    public void addMember(MemberRepository memberRepository) {
        Member member = Registration();
        memberRepository.save(member);
    }

    public String getID() {
        return ID;
    }
    public String getPW() {
        return PW;
    }
    public String getNickname() {
        return nickname;
    }
    public String getEmail() {
        return email;
    }
}
