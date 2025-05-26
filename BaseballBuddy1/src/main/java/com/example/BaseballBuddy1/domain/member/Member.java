package com.example.BaseballBuddy1.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
    @Id
    private String MemberID;
    private String MemberPW;
    private String nickname;
    private String email;

    protected Member() {
    } // JPA 기본 생성자

    // 생성자
    public Member(String MemberID, String MemberPW, String nickname, String email) {
        this.MemberID = MemberID;
        this.MemberPW = MemberPW;
        this.nickname = nickname;
        this.email = email;
    }

}
