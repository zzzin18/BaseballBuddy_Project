package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.member.Member;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class AttendPost extends Post{
    private LocalDateTime postDeadline;

    public AttendPost(String postTitle, String postDetail, LocalDateTime postDeadline, Member postMember) {
        super(postTitle, postDetail, postMember);
        this.postDeadline = postDeadline;
    }

    public LocalDateTime getPostDeadline() {
        return postDeadline;
    }
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(postDeadline);
    }

    public void updateAttendPost(String postTitle, String postDetail, LocalDateTime postDeadline) {
        if (!isExpired()) {
            super.updatePost(postTitle, postDetail);
            this.postDeadline = postDeadline;
        } else {
            throw new IllegalStateException("모집 마감 기한이 끝난 글은 수정할 수 없습니다.");
        }
    }

    @Override
    public boolean canModify(Member currentMember) {
        return !isExpired();
    }
    @Override
    public void updatePost(String postTitle, String postDetail) {
        if (!isExpired()) {
            super.setPostTitle(postTitle);
            super.setPostDetail(postDetail);
        } else {
            throw new IllegalStateException("마감된 글은 수정할 수 없습니다.");
        }
    }
    @Override
    public boolean delete(Member currentMember) {
        return !isExpired();
    }
}

