package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.post.ReviewPost;
import com.example.BaseballBuddy1.domain.post.ReviewPostRepository;
import com.example.BaseballBuddy1.domain.member.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewPostService {
    private final ReviewPostRepository reviewPostRepository;

    public ReviewPost createReviewPost(String title, String detail, Member member, Stadium stadium, FilterTag filter, List<String> imageList) {
        ReviewPost post = new ReviewPost(title, detail, member, stadium, filter);
        if (imageList != null && !imageList.isEmpty()) {
            post.getImage().addAll(imageList);
        }
        return reviewPostRepository.save(post);
    }

    public Optional<ReviewPost> getReviewPost(String id) {
        return reviewPostRepository.findById(id);
    }
    public List<ReviewPost> getAllReviewPosts() {
        return reviewPostRepository.findAllByOrderByCreatedAtDesc();
    }

    public ReviewPost updateReviewPost(String postId, String title, String detail, Member member, Stadium stadium, FilterTag filter,  List<String> imageList) {
        ReviewPost post = reviewPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        post.updateReviewPost(title, detail, stadium, filter, imageList);
        return post;
    }
    // 후기 삭제
    public void deleteReviewPost(String postId) {
        reviewPostRepository.deleteById(postId);
    }
}
