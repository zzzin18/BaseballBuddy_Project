package com.example.BaseballBuddy1.domain.member;

import com.example.BaseballBuddy1.domain.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Login {
    @Id
    private String ID;
    private String PW;

    protected Login() {}

    public Login(String ID, String PW) {
        this.ID = ID;
        this.PW = PW;
    }

    public String getID() {
        return ID;
    }
    public String getPW() {
        return PW;
    }
    public boolean memberCheck(Member member) {
        return this.ID.equals(member.getID()) && this.PW.equals(member.getPW());
    }

}
