package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.post.ReviewPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPostRepository extends JpaRepository<ReviewPost, String> {
    List<ReviewPost> findAllByOrderByCreatedAtDesc();

    List<ReviewPost> findByPostMember_MemberID(String memberID);
}
