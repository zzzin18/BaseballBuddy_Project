package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.CommentRequest;
import com.example.BaseballBuddy1.controller.dto.CommentResponse;
import com.example.BaseballBuddy1.domain.comment.Comment;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.post.AttendPost;
import com.example.BaseballBuddy1.service.CommentService;
import com.example.BaseballBuddy1.service.AttendPostService;
import com.example.BaseballBuddy1.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final AttendPostService attendPostService;
    private final MemberService memberService;

    // 댓글 등록
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(
            @RequestParam String commentDetail,
            @RequestParam String postId,
            @RequestParam String memberId
    ){
        Optional<AttendPost> postOpt = attendPostService.getPostById(postId);
        Optional<Member> memberOpt = memberService.getMemberById(memberId);

        Comment savedComment = commentService.addComment(commentDetail, memberOpt.get(), postOpt.get());
        return ResponseEntity.ok(savedComment);
    }

    // 댓글 수정
    @PutMapping("/{commentId}/update")
    public ResponseEntity<String> updateComment(
            @PathVariable String commentId,
            @RequestParam String newDetail,
            @RequestParam String memberId
    ){
        Optional<Member> memberOpt = memberService.getMemberById(memberId);
        try {
            commentService.updateComment(commentId, newDetail, memberOpt.get());
            return ResponseEntity.ok("댓글이 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<String> deleteComment(
            @PathVariable String commentId,
            @RequestParam String MemberId
    ) {
        Optional<Member> memberOpt = memberService.getMemberById(MemberId);
        try {
            commentService.deleteComment(commentId, memberOpt.get());
            return ResponseEntity.ok("댓글이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable String postId) {
        System.out.println("받은 postId: " + postId);
        Optional<AttendPost> postOptional = attendPostService.getPostById(postId);
        if (postOptional.isEmpty()) {
            System.out.println("해당 postId 없음!");
            return ResponseEntity.notFound().build();
        }
        List<Comment> comments = commentService.getCommentsByPost(postOptional.get());
        List<CommentResponse> response = comments.stream()
                .map(CommentResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/posts/{postId}/comments")
    public ResponseEntity<?> createCommentFromPostApi(
            @PathVariable String postId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal Member member
    ) {
        Optional<AttendPost> postOpt = attendPostService.getPostById(postId);
        if (postOpt.isEmpty()) return ResponseEntity.notFound().build();

        Comment saved = commentService.addComment(request.getContent(), member, postOpt.get());
        return ResponseEntity.ok().build();
    }
}