package com.example.BaseballBuddy1.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AttendPostRequest {
    private String postTitle;
    private String postDetail;
    private LocalDateTime postDeadline;
}
