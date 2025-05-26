package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.SharePostRequest;
import com.example.BaseballBuddy1.controller.dto.SharePostResponse;
import com.example.BaseballBuddy1.service.SharePostService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/share")
public class SharePostController {

    private SharePostService sharePostService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody SharePostRequest request, @RequestParam String memberID) {
        return ResponseEntity.ok(sharePostService.createPost(request, memberID));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharePostResponse> getPost(@PathVariable String ID, @RequestParam String memberID) {
        return ResponseEntity.ok(sharePostService.getPost(ID, memberID));
    }
    @GetMapping
    public ResponseEntity<List<SharePostResponse>> getAllPosts(@RequestParam(required = false) String memberId) {
        return ResponseEntity.ok(sharePostService.getAllPosts(memberId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable String ID, @RequestBody SharePostRequest request, @RequestParam String memberID) {
        sharePostService.updatePost(ID, request, memberID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String ID, @RequestParam String memberID) {
        sharePostService.deletePost(ID, memberID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable String ID, @RequestParam String memberID) {
        sharePostService.likePost(ID, memberID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/unlike")
    public ResponseEntity<Void> unlikePost(@PathVariable String ID, @RequestParam String memberID) {
        sharePostService.unlikePost(ID, memberID);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/like-toggle")
    public ResponseEntity<Void> toggleLikes(@PathVariable String ID, @RequestParam String memberID) {
        sharePostService.toggleLike(ID, memberID);
        return ResponseEntity.ok().build();
    }

}

