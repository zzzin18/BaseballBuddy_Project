package com.example.BaseballBuddy1.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterationRequest {
    private String ID;
    private String PW;
    private String nickname;
    private String email;

}
