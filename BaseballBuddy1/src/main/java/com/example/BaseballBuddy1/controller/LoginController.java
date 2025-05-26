package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.LoginRequest;
import com.example.BaseballBuddy1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean loginSuccess = memberService.memberCheck(loginRequest.getID(), loginRequest.getPW());
        if (loginSuccess) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }
}
