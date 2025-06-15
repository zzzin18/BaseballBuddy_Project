package com.example.BaseballBuddy1.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewPostRepository extends JpaRepository<ReviewPost, String> {
    List<ReviewPost> findAllByOrderByCreatedAtDesc();
    List<ReviewPost> findByPostMemberMemberId(String memberID);
    List<ReviewPost> findByLikedMembersMemberId(String memberId);
    @Query("select rp from ReviewPost rp join fetch rp.postMember order by rp.createdAt desc")
    List<ReviewPost> findAllWithMember();

    List<ReviewPost> findAllByOrderByPostDateDesc();
}

