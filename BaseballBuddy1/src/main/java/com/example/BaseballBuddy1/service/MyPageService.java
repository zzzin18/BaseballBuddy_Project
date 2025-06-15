package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.controller.dto.AttendPostResponse;
import com.example.BaseballBuddy1.controller.dto.CommentResponse;
import com.example.BaseballBuddy1.controller.dto.ReviewPostResponse;
import com.example.BaseballBuddy1.domain.comment.CommentRepository;
import com.example.BaseballBuddy1.domain.post.AttendPostRepository;
import com.example.BaseballBuddy1.domain.post.ReviewPostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final AttendPostRepository attendPostRepository;
    private final ReviewPostRepository reviewPostRepository;
    private final CommentRepository commentRepository;

    public List<AttendPostResponse> getAttendPosts(String memberId) {
        return attendPostRepository.findByPostMemberMemberId(memberId).stream()
                .map(AttendPostResponse::from)
                .toList();
    }

    public List<ReviewPostResponse> getReviewPosts(String memberId) {
        return reviewPostRepository.findByPostMemberMemberId(memberId).stream()
                .map(ReviewPostResponse::from)
                .toList();
    }
    public List<CommentResponse> getComments(String memberId) {
        return commentRepository.findByCommentMember_MemberId(memberId).stream()
                .map(CommentResponse::from)
                .toList();
    }

    public List<ReviewPostResponse> getLikedReviewPosts(String memberId) {
        return reviewPostRepository.findByLikedMembersMemberId(memberId).stream()
                .map(ReviewPostResponse::from)
                .toList();
    }
}

