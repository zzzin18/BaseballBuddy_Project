package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.post.AttendPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendPostRepository extends JpaRepository<AttendPost, Long> {
    List<AttendPost> findAllByOrderByCreatedAtDesc();

    List<AttendPost> findByPostMember_MemberID(String memberId);
}
