package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.AttendPostRequest;
import com.example.BaseballBuddy1.domain.post.AttendPost;
import com.example.BaseballBuddy1.service.AttendPostService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attend-posts")
@RequiredArgsConstructor
public class AttendPostController {
    private final AttendPostService attendPostService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody AttendPostRequest request) {
        attendPostService.createPost(request);
        return ResponseEntity.ok("글 작성 완료");
    }

    @GetMapping
    public List<AttendPost> getAllPosts() {
        return attendPostService.getAllPosts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody AttendPostRequest request) {
        attendPostService.updatePost(id, request);
        return ResponseEntity.ok("글 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        attendPostService.deletePost(id);
        return ResponseEntity.ok("글 삭제 완료");
    }
}
