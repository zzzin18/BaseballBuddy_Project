package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.post.AttendPost;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AttendPostResponse {
    private String postID;
    private String postTitle;
    private String postDetail;
    private LocalDateTime postDate;

    public static AttendPostResponse from(AttendPost post) {
        return new AttendPostResponse(
                post.getPostID(),
                post.getPostTitle(),
                post.getPostDetail(),
                post.getPostDate()
        );
    }
}

