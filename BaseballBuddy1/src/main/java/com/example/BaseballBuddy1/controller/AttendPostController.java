package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.*;
import com.example.BaseballBuddy1.domain.post.AttendPost;
import com.example.BaseballBuddy1.service.AttendPostService;
import com.example.BaseballBuddy1.domain.post.AttendPostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/attend-posts")
@RequiredArgsConstructor
public class AttendPostController {
    private final AttendPostService attendPostService;
    private final AttendPostRepository attendPostRepository;


    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody AttendPostRequest request) {
        attendPostService.createPost(request);
        return ResponseEntity.ok("글 작성 완료");
    }

    @GetMapping
    public List<AttendPost> getAllPosts() {
        return attendPostService.getAllPosts();
    }

    @GetMapping("/by-date")
    public List<AttendPostResponse> getPostsByDate(@RequestParam("date") LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<AttendPost> all = attendPostRepository.findAll();
        for (AttendPost post : all) {
            System.out.println("[전체조회] title=" + post.getPostTitle() + ", attendDate=" + post.getAttendDate());
        }

        List<AttendPost> posts = attendPostRepository.findByAttendDateBetween(startOfDay, endOfDay);

        System.out.println("받은 날짜: " + date);
        System.out.println("쿼리 범위: " + startOfDay + " ~ " + endOfDay);
        System.out.println("posts.size(): " + posts.size());
        for (AttendPost post : posts) {
            System.out.println("[확인] " + post.getPostTitle() + " | attendDate=" + post.getAttendDate());
        }

        return posts.stream()
                .map(AttendPostResponse::from)
                .toList();
    }


    @GetMapping("/{postId}")
    public ResponseEntity<AttendPostResponse> getPostById(@PathVariable String postId) {
        return attendPostService.getPostById(postId)
                .map(AttendPostResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable String Id, @RequestBody AttendPostRequest request) {
        attendPostService.updatePost(Id, request);
        return ResponseEntity.ok("글 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String Id) {
        attendPostService.deletePost(Id);
        return ResponseEntity.ok("글 삭제 완료");
    }

    @PostMapping("/recommend")
    public ResponseEntity<List<AttendPostSummaryResponse>> recommend(@RequestBody RecommendRequest request) {
        // 일단 더미 데이터라도 만들어서 리턴
        List<AttendPostSummaryResponse> dummyList = List.of(
                new AttendPostSummaryResponse(1L, "추천 글 제목 1", "내용 1"),
                new AttendPostSummaryResponse(2L, "추천 글 제목 2", "내용 2")
        );
        return ResponseEntity.ok(dummyList);
    }


}
