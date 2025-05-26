package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.RegisterationRequest;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입 POST 요청
    @PostMapping("/registration")
    public ResponseEntity<String> registeration(@RequestBody RegisterationRequest RegisterationRequest) {
        try {
            Member member = new Member(
                    RegisterationRequest.getID(),
                    RegisterationRequest.getPW(),
                    RegisterationRequest.getNickname(),
                    RegisterationRequest.getEmail()
            );

            memberService.registerMember(member);

            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류 발생");
        }
    }
}
