package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.controller.dto.ReviewPostResponse;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.post.ReviewPost;
import com.example.BaseballBuddy1.domain.post.ReviewPostRepository;
import com.example.BaseballBuddy1.domain.member.MemberRepository;
import com.example.BaseballBuddy1.domain.member.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewPostService {
    private final ReviewPostRepository reviewPostRepository;
    private final MemberRepository memberRepository;

    public List<ReviewPost> getAllReviewPostsSortedByCreatedAtDesc() {
        return reviewPostRepository.findAllByOrderByCreatedAtDesc();
    }

    public void createReviewPost(String postTitle, String postDetail, Member member, Stadium stadium, FilterTag filter) {
        if (member == null) {
            throw new IllegalArgumentException("작성자 정보가 없습니다.");
        }
        System.out.println("저장되는 Member: " + member);
        ReviewPost post = new ReviewPost(postTitle, postDetail, member, stadium, filter);
        reviewPostRepository.save(post);
    }



    public Optional<ReviewPost> getReviewPost(String id) {
        return reviewPostRepository.findById(id);
    }

    public List<ReviewPost> getAllReviewPosts() {
        return reviewPostRepository.findAllByOrderByPostDateDesc();
    }


    public ReviewPost updateReviewPost(String postId, String title, String detail, Member member, Stadium stadium, FilterTag filter, List<String> imageList) {
        ReviewPost post = reviewPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        // 작성자 확인 (멤버가 맞는지 체크)
        if (!post.getPostMember().getMemberId().equals(member.getMemberId())) {
            throw new IllegalStateException("작성자만 수정 가능합니다.");
        }

        post.updateReviewPost(title, detail, stadium, filter, imageList);
        return post;
    }

    public void deleteReviewPost(String postId, String memberId) {
        ReviewPost post = reviewPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        if (!post.getPostMember().getMemberId().equals(memberId)) {
            throw new IllegalStateException("작성자만 삭제 가능합니다.");
        }

        reviewPostRepository.delete(post);
    }

    public List<ReviewPost> getPostsByMemberID(String memberID) {
        return reviewPostRepository.findByPostMemberMemberId(memberID);
    }

    public void toggleLike(String postId, Member member) {
        ReviewPost post = reviewPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (post.getLikedMembers().contains(member)) {
            post.removeLike(member);
        } else {
            post.addLike(member);
        }

        reviewPostRepository.save(post);
    }

    public List<ReviewPostResponse> findByFilters(Stadium stadium, List<FilterTag> filters) {
        List<ReviewPost> allPosts = reviewPostRepository.findAll();

        // 필터링
        List<ReviewPost> filtered = allPosts.stream()
                .filter(post -> stadium == null || stadium.equals(post.getStadium()))
                .filter(post -> {
                    if (filters == null || filters.isEmpty()) return true;
                    if (post.getFilterTags() == null) return false;
                    return post.getFilterTags().stream().anyMatch(filters::contains);
                })

                .collect(Collectors.toList());

        // DTO 변환
        return filtered.stream()
                .map(ReviewPostResponse::fromEntity)  // fromEntity 메서드 있어야 함
                .collect(Collectors.toList());
    }
}


