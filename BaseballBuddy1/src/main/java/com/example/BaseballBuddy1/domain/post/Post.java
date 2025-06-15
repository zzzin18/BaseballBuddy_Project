package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.member.Member;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;

@MappedSuperclass
@Getter @Setter
public abstract class Post {
    @Id
    private String postId;
    @Setter
    private String postTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    protected Member postMember;
    @Setter
    private String postDetail;
    @Getter
    private LocalDateTime postDate;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    protected Post() {}

    protected Post(String postTitle, String postDetail, Member postMember) {
        this.postId = UUID.randomUUID().toString();
        this.postTitle = postTitle;
        this.postDetail = postDetail;
        this.postDate = LocalDateTime.now();
        this.postMember = postMember;
    }

    public boolean canModify(Member currentMember) {
        return this.postMember.equals(currentMember);
    }
    public void updatePost(String postTitle, String postDetail) {
        this.postTitle = postTitle;
        this.postDetail = postDetail;
    }
    public boolean delete(Member currentMember) {
        return this.postMember.equals(currentMember);
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}