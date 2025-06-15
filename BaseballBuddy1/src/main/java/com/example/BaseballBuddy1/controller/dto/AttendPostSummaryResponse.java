package com.example.BaseballBuddy1.controller.dto;

import lombok.Getter;

@Getter
public class AttendPostSummaryResponse {
    private Long postID;
    private String postTitle;
    private String postDetail;


    public AttendPostSummaryResponse(Long postID, String postTitle, String postDetail) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postDetail = postDetail;
    }
}
