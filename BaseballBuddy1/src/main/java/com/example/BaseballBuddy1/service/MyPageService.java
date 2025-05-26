package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.controller.dto.AttendPostResponse;
import com.example.BaseballBuddy1.controller.dto.CommentResponse;
import com.example.BaseballBuddy1.controller.dto.ReviewPostResponse;
import com.example.BaseballBuddy1.controller.dto.SharePostResponse;
import com.example.BaseballBuddy1.domain.comment.CommentRepository;
import com.example.BaseballBuddy1.domain.member.MemberRepository;
import com.example.BaseballBuddy1.domain.post.AttendPostRepository;
import com.example.BaseballBuddy1.domain.post.ReviewPostRepository;
import com.example.BaseballBuddy1.domain.post.SharePostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final AttendPostRepository attendPostRepository;
    private final ReviewPostRepository reviewPostRepository;
    private final SharePostRepository sharePostRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public List<AttendPostResponse> getAttendPosts(String memberID) {
        return attendPostRepository.findByPostMember_MemberID(memberID).stream()
                .map(AttendPostResponse::from)
                .toList();
    }

    public List<ReviewPostResponse> getReviewPosts(String memberID) {
        return reviewPostRepository.findByPostMember_MemberID(memberID).stream()
                .map(ReviewPostResponse::from)
                .toList();
    }

    public List<SharePostResponse> getSharePosts(String memberID) {
        return sharePostRepository.findByPostMember_MemberID(memberID).stream()
                .map(SharePostResponse::from)
                .toList();
    }

    public List<CommentResponse> getComments(String memberID) {
        return commentRepository.findByMember_MemberID(memberID).stream()
                .map(CommentResponse::from)
                .toList();
    }

    public List<SharePostResponse> getLikedSharePosts(String memberID) {
        return sharePostRepository.findByLikeMember_MemberID(memberID).stream()
                .map(SharePostResponse::from)
                .toList();
    }
}

