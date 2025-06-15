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
    private String nickname; // 작성자 닉네임 또는 ID
    private LocalDateTime createdAt;
    private String relatedPostId;

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getCommentDetail(),
                comment.getMember().getNickname(),
                comment.getCommentCreatedAt(),
                comment.getAttendPost().getPostId()
        );
    }
}
