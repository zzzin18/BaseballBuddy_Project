package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.ReviewPostRequest;
import com.example.BaseballBuddy1.controller.dto.ReviewPostResponse;
import com.example.BaseballBuddy1.controller.dto.LoginUserResponse;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.member.MemberRepository;
import com.example.BaseballBuddy1.domain.post.ReviewPost;
import com.example.BaseballBuddy1.service.ReviewPostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review-posts")
@RequiredArgsConstructor
public class ReviewPostController {
    private final ReviewPostService reviewPostService;
    private final MemberRepository memberRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createReviewPost(@RequestBody ReviewPostRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loginId = authentication.getName();
            Member member = memberRepository.findByMemberId(loginId)
                    .orElseThrow(() -> new IllegalArgumentException("로그인이 필요한 서비스입니다."));

            reviewPostService.createReviewPost(request.getPostTitle(), request.getPostDetail(),
                    member, request.getStadium(), request.getFilter());

            return ResponseEntity.ok("구장 후기 게시글 작성 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생: " + e.getMessage());
        }
    }


    @GetMapping("/filter")
    public List<ReviewPostResponse> getFilteredPosts(
            @RequestParam(required = false) Stadium stadium,
            @RequestParam(required = false) List<FilterTag> filters) {

        System.out.println("filter 요청 - stadium: " + stadium + " / filters: " + filters);
        return reviewPostService.findByFilters(stadium, filters);
    }

    @GetMapping
    public ResponseEntity<List<ReviewPostResponse>> getAllReviewPosts() {
        List<ReviewPost> posts = reviewPostService.getAllReviewPostsSortedByCreatedAtDesc();
        System.out.println("조회된 게시글 수: " + posts.size());

        List<ReviewPostResponse> responses = posts.stream()
                .map(ReviewPostResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/my/{memberId}")
    public ResponseEntity<List<ReviewPostResponse>> getMyReviewPosts(@PathVariable String memberId) {
        List<ReviewPostResponse> posts = reviewPostService.getPostsByMemberID(memberId)
                .stream()
                .map(ReviewPostResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReviewPost(@PathVariable String id, @RequestBody ReviewPostRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loginId = authentication.getName();
            Member member = memberRepository.findByMemberId(loginId)
                    .orElseThrow(() -> new IllegalArgumentException("로그인이 필요한 서비스입니다."));

            Stadium stadium = request.getStadium();
            FilterTag filter = request.getFilter();

            // 여기서도 imageList 제거하고 호출
            reviewPostService.updateReviewPost(
                    id,
                    request.getPostTitle(),
                    request.getPostDetail(),
                    member,
                    request.getStadium(),
                    request.getFilter(),
                    new ArrayList<>()
            );

            return ResponseEntity.ok("구장 후기 게시글 수정 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("stadium 또는 filter 값이 유효하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류 발생");
        }
    }

    @DeleteMapping("/{postID}")
    public ResponseEntity<Void> deleteReviewPost(@PathVariable String postID, @RequestParam String memberId) {
        reviewPostService.deleteReviewPost(postID, memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        String loginId = authentication.getName();
        Member member = memberRepository.findByMemberId(loginId)
                .orElse(null);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("회원 정보 없음");
        }
        // 필요한 정보만 반환 (예: 아이디, 이름 등)
        return ResponseEntity.ok(new LoginUserResponse(member.getMemberId(), member.getNickname()));
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<String> toggleLike(@PathVariable String postId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loginId = authentication.getName();
            Member member = memberRepository.findByMemberId(loginId)
                    .orElseThrow(() -> new IllegalArgumentException("로그인이 필요한 서비스입니다."));

            reviewPostService.toggleLike(postId, member);

            return ResponseEntity.ok("좋아요 상태 변경 완료");
        } catch (Exception e) {
            e.printStackTrace();  // 에러 상세 로그 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("좋아요 변경 실패: " + e.getMessage());
        }
    }
}
