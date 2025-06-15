package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Member member) {
        try {
            Member saved = memberService.registration(member);
            return ResponseEntity.ok(saved);
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentMemberInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Member member = (Member) authentication.getPrincipal();
        return ResponseEntity.ok(Map.of(
                "id", member.getId(),
                "memberId", member.getMemberId(),
                "nickname", member.getNickname()
        ));
    }

    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkSameId(@RequestParam String memberId) {
        System.out.println("중복 체크 요청: " + memberId);
        boolean exists = memberService.getMemberById(memberId).isPresent();
        System.out.println("중복 여부: " + exists);
        return ResponseEntity.ok(exists);  // 중복이면 false, 아니면 true
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkDuplicateEmail(@RequestParam String email) {
        boolean exists = memberService.getMemberByEmail(email).isPresent();
        return ResponseEntity.ok(exists);
    }


}
