package com.example.BaseballBuddy1.domain.comment;

import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.post.AttendPost;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String commentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member commentMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attend_post_id")
    private AttendPost attendPost;

    private String commentDetail;
    private LocalDateTime commentCreatedAt;

    protected Comment() {} // JPA 기본 생성자

    // 생성자
    public Comment(String commentDetail, Member commentMember, AttendPost attendPost) {
        this.commentID = UUID.randomUUID().toString();
        this.commentDetail = commentDetail;
        this.commentMember = commentMember;
        this.attendPost = attendPost;
        this.commentCreatedAt = LocalDateTime.now();
    }

    public void updateComment(String commentDetail) {
        this.commentDetail = commentDetail;
    }
    public boolean canModify(Member currentUser) {
        return commentMember.getMemberID().equals(currentUser.getMemberID());
    }
    public void delete() {
        // 실삭제
    }

    public Member getMember(){
        return commentMember;
    }

}
