package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.controller.dto.*;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.member.MemberRepository;
import com.example.BaseballBuddy1.domain.post.AttendPost;
import com.example.BaseballBuddy1.domain.post.AttendPostRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class AttendPostService {

    private final AttendPostRepository attendPostRepository;
    private final MemberRepository memberRepository;

    public void createPost(AttendPostRequest request) {
        System.out.println("attendDate 들어온 값: " + request.getAttendDate());

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("작성자 정보가 없습니다."));

        AttendPost post = new AttendPost(
                request.getPostTitle(),
                request.getPostDetail(),
                member,
                request.getAttendDate()
        );

        attendPostRepository.save(post);
    }


    private boolean isInvalidRequest(AttendPostRequest request) {
        return request.getPostTitle() == null || request.getPostTitle().trim().isEmpty()
                || request.getPostDetail() == null || request.getPostDetail().trim().isEmpty();

    }

    public List<AttendPost> getAllPosts() {
        return attendPostRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<AttendPost> getPostById(String postId) {
        return attendPostRepository.findById(postId);
    }

    /*
    public List<AttendPostSummaryResponse> getPostsByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        List<AttendPost> posts = attendPostRepository.findByAttendDateBetween(start, end); // ✅ BETWEEN 사용 중요!
        return posts.stream()
                .map(AttendPostSummaryResponse::fromEntity)
                .collect(Collectors.toList());
    }*/


    public void updatePost(String Id, AttendPostRequest request) {
        if (isInvalidRequest(request)) {
            throw new IllegalArgumentException("모두 입력하세요.");
        }
        AttendPost post = attendPostRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        post.updateAttendPost(request.getPostTitle(), request.getPostDetail());
    }

    public void deletePost(String Id) {
        AttendPost post = attendPostRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        attendPostRepository.delete(post);
    }

    public Optional<Member> getMemberById(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public AttendPostService(AttendPostRepository attendPostRepository, MemberRepository memberRepository) {
        this.attendPostRepository = attendPostRepository;
        this.memberRepository = memberRepository;
    }

    public List<AttendPostResponse> recommendPosts(SurveyRequest request) {
        List<AttendPost> allPosts = attendPostRepository.findAll();
        List<AttendPost> matchedPosts = new ArrayList<>();

        for (AttendPost post : allPosts) {
            int matchCount = 0;

            if (post.getPostDate().equals(request.getDate())) matchCount++;
            if (post.getStadium() == request.getStadium()) matchCount++;
            if (post.getSeatType() == request.getSeatType()) matchCount++;
            if (post.getCheerStyle() == request.getCheerStyle()) matchCount++;
            if (post.getGroupType() == request.getGroupType()) matchCount++;

            if (matchCount >= 3) {
                matchedPosts.add(post);
            }
        }

        return matchedPosts.stream()
                .map(AttendPostResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //추천글 불러오기
    /*
    public List<AttendPostResponse> findRecommendedPosts(RecommendRequest request) {
        List<AttendPost> posts = attendPostRepository.findAll(); // 실제론 필터 기준으로 줄이기
        return posts.stream()
                .filter(p -> {
                    long matchCount = request.getSelectedFilters().stream().filter(selected ->
                            selected.equals(p.getStadium().toString()) ||
                                    selected.equals(p.getSeatType().toString()) ||
                                    selected.equals(p.getCheerStyle().toString()) ||
                                    selected.equals(p.getGroupType().toString())
                    ).count();
                    return matchCount >= 3;
                })
                .limit(3)
                .map(AttendPostResponse::from)
                .toList();
    }
    */

}
