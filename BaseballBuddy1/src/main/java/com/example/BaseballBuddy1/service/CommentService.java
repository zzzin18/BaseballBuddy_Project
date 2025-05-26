package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.domain.comment.Comment;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.post.AttendPost;

import com.example.BaseballBuddy1.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment addComment(String commentDetail, Member member, AttendPost post) {
        Comment comment = new Comment(commentDetail, member, post);
        return commentRepository.save(comment);
    }
    public void updateComment(String commentID, String newDetail, Member currentUser) {
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (!comment.canModify(currentUser)) {
            throw new IllegalStateException("댓글 작성자만 수정할 수 있습니다.");
        }

        comment.updateComment(newDetail);
        commentRepository.save(comment);
    }
    public void deleteComment(String commentID, Member currentUser) {
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (!comment.canModify(currentUser)) {
            throw new IllegalStateException("댓글 작성자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }
    public List<Comment> getCommentsByPost(AttendPost post) {
        return commentRepository.findByAttendPostOrderByCommentCreatedAtAsc(post);
    }

}
