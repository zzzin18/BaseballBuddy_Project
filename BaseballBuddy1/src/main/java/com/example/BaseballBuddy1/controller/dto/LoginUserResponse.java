package com.example.BaseballBuddy1.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginUserResponse {
    private String memberId;
    private String nickname;

    public LoginUserResponse(String memberId, String nickname) {
        this.memberId = memberId;
        this.nickname = nickname;
    }
}
