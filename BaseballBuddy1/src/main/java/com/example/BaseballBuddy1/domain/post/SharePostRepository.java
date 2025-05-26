package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.post.SharePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharePostRepository extends JpaRepository<SharePost, String> {
    static List<SharePost> findAllByOrderByPostDateDesc() {
        return null; // 연결필요
    }

    List<SharePost> findByPostMember_MemberID(String memberId);
    List<SharePost> findByLikeMember_MemberID(String memberId);
}
