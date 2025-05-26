package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.AttendPostResponse;
import com.example.BaseballBuddy1.controller.dto.ReviewPostResponse;
import com.example.BaseballBuddy1.controller.dto.SharePostResponse;
import com.example.BaseballBuddy1.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/{memberId}/attends")
    public ResponseEntity<List<AttendPostResponse>> getAttendPosts(@PathVariable String memberId) {
        return ResponseEntity.ok(myPageService.getAttendPosts(memberId));
    }

    @GetMapping("/{memberId}/reviews")
    public ResponseEntity<List<ReviewPostResponse>> getReviewPosts(@PathVariable String memberId) {
        return ResponseEntity.ok(myPageService.getReviewPosts(memberId));
    }

    @GetMapping("/{memberId}/shares")
    public ResponseEntity<List<SharePostResponse>> getSharePosts(@PathVariable String memberId) {
        return ResponseEntity.ok(myPageService.getSharePosts(memberId));
    }

    @GetMapping("/{memberId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable String memberId) {
        return ResponseEntity.ok(myPageService.getComments(memberId));
    }

    @GetMapping("/{memberId}/likes")
    public ResponseEntity<List<SharePostResponse>> getLikedPosts(@PathVariable String memberId) {
        return ResponseEntity.ok(myPageService.getLikedSharePosts(memberId));
    }
}
