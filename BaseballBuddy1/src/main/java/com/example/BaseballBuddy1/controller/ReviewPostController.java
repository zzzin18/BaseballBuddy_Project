package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.ReviewPostRequest;
import com.example.BaseballBuddy1.controller.dto.ReviewPostResponse;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.member.MemberRepository;
import com.example.BaseballBuddy1.domain.post.ReviewPost;
import com.example.BaseballBuddy1.service.ReviewPostService;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review-posts")
@RequiredArgsConstructor
public class ReviewPostController {
    private final ReviewPostService reviewPostService;
    private final MemberRepository memberRepository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createReviewPost(@RequestBody ReviewPostRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loginId = authentication.getName();
            Member member = memberRepository.findByID(loginId).orElseThrow(() -> new IllegalArgumentException("로그인이 필요한 서비스입니다.."));
            Stadium stadium = request.getStadium();
            FilterTag filter = request.getFilter();

            reviewPostService.createReviewPost(request.getPostTitle(), request.getPostDetail(), member, stadium, filter, request.getImage());

            return ResponseEntity.ok("구장 후기 게시글 작성 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("stadium 또는 filter 값이 유효하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류 발생");
        }

    }

    @GetMapping
    public ResponseEntity<List<ReviewPostResponse>> getAllReviewPosts() {
        List<ReviewPost> posts = reviewPostService.getAllReviewPosts();
        List<ReviewPostResponse> responses = posts.stream()
                .map(ReviewPostResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewPostResponse> getReviewPost(@PathVariable String id) {
        return reviewPostService.getReviewPost(id)
                .map(post -> ResponseEntity.ok(new ReviewPostResponse(post)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReviewPost(@PathVariable String id, @RequestBody ReviewPostRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loginId = authentication.getName();
            Member member = memberRepository.findByID(loginId)
                    .orElseThrow(() -> new IllegalArgumentException("로그인이 필요한 서비스입니다."));

            Stadium stadium = request.getStadium();
            FilterTag filter = request.getFilter();

            reviewPostService.updateReviewPost(id,request.getPostTitle(),request.getPostDetail(),member,stadium,filter,request.getImage());

            return ResponseEntity.ok("구장 후기 게시글 수정 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("stadium 또는 filter 값이 유효하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류 발생");
        }
    }


    // 후기 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewPost(@PathVariable String id) {
        reviewPostService.deleteReviewPost(id);
        return ResponseEntity.noContent().build();
    }
}
