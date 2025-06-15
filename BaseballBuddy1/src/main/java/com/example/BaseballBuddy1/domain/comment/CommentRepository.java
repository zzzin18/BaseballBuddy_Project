package com.example.BaseballBuddy1.domain.comment;

import com.example.BaseballBuddy1.domain.post.AttendPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByAttendPostOrderByCommentCreatedAtAsc(AttendPost post);
    List<Comment> findByCommentMember_MemberId(String memberId);
    List<Comment> findByAttendPost_PostId(String postId);

}
