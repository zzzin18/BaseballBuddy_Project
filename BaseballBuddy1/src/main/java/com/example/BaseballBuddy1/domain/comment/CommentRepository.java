package com.example.BaseballBuddy1.domain.comment;

import com.example.BaseballBuddy1.domain.comment.Comment;
import com.example.BaseballBuddy1.domain.post.AttendPost;
import com.example.BaseballBuddy1.domain.post.SharePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByAttendPostOrderByCommentCreatedAtAsc(AttendPost post);

    List<Comment> findByMember_MemberID(String memberID);
}
