package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.domain.comment.Comment;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.post.AttendPost;
import com.example.BaseballBuddy1.service.CommentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//사용자 불러와야해


@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentControler {
    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(
            @RequestParam String commentDetail,
            @RequestParam String postId,
            @RequestParam String memberId
    ) {
        // 실제 프로젝트에선 postId, memberId로 DB에서 찾아야 함 (생략)
        AttendPost post = new AttendPost(); // 더미
        Member member = new Member(); // 더미

        Comment savedComment = commentService.addComment(commentDetail, member, post);
        return ResponseEntity.ok(savedComment);  //댓글 작성 완료 멘트 필요
    }

    // 댓글 수정
    @PutMapping("/{commentId}/update")
    public ResponseEntity<String> updateComment(
            @PathVariable String commentId,
            @RequestParam String newDetail,
            @RequestParam String memberId
    ) {
        Member member = new Member(); // 더미
        try {
            commentService.updateComment(commentId, newDetail, member);
            return ResponseEntity.ok("댓글이 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<String> deleteComment(
            @PathVariable String commentId,
            @RequestParam String memberId
    ) {
        Member member = new Member(); // 더미
        try {
            commentService.deleteComment(commentId, member);
            return ResponseEntity.ok("댓글이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 게시글에 달린 댓글 조회
    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable String postId) {
        AttendPost post = new AttendPost(); // 더미
        List<Comment> comments = commentService.getCommentsByPost(post);
        return ResponseEntity.ok(comments);
    }
}
