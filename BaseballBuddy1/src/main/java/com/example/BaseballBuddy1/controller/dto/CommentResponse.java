package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponse {

    private String commentID;
    private String content;
    private String writer; // 작성자 닉네임 또는 ID
    private LocalDateTime createdAt;
    private String relatedPostId; // 어떤 게시글에 달린 댓글인지

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getCommentID(),
                comment.getContent(),
                comment.getMember().getNickname(), // 또는 getMemberID()
                comment.getCreatedAt(),
                comment.getPost().getPostID() // 어떤 게시글에 달린 댓글인지
        );
    }
}
