package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.CheerStyle;
import com.example.BaseballBuddy1.domain.GroupType;
import com.example.BaseballBuddy1.domain.SeatType;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.post.AttendPost;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class AttendPostResponse {
    private String postID;
    private String postTitle;
    private String postDetail;
    private String nickname;
    private LocalDateTime attendDate;

    private Stadium stadium;
    private SeatType seatType;
    private CheerStyle cheerStyle;
    private GroupType groupType;

    public static AttendPostResponse from(AttendPost post) {
        return fromEntity(post);
    }

    public AttendPostResponse(String postID, String postTitle, String postDetail, String nickname, LocalDateTime attendDate) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postDetail = postDetail;
        this.nickname = nickname;
        this.attendDate = attendDate;
    }

    public AttendPostResponse(String postID, String postTitle, String postDetail, String nickname, LocalDateTime attendDate,
                              Stadium stadium, SeatType seatType, CheerStyle cheerStyle, GroupType groupType) {
        this(postID, postTitle, postDetail, nickname, attendDate);
        this.stadium = stadium;
        this.seatType = seatType;
        this.cheerStyle = cheerStyle;
        this.groupType = groupType;
    }

    public static AttendPostResponse fromEntity(AttendPost post) {
        return AttendPostResponse.builder()
                .postID(post.getPostId())
                .postTitle(post.getPostTitle())
                .postDetail(post.getPostDetail())
                .nickname(post.getPostMember().getNickname())
                .attendDate(post.getAttendDate())
                .stadium(post.getStadium())
                .seatType(post.getSeatType())
                .cheerStyle(post.getCheerStyle())
                .groupType(post.getGroupType())
                .build();
    }

}

