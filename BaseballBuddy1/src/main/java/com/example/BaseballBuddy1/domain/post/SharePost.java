package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class SharePost extends Post {

    @ElementCollection
    private List<String> imageList = new ArrayList<>();

    private LocalDateTime postDeadline;

    @ManyToMany
    private List<Member> likeMember = new ArrayList<>();

    protected SharePost() {}


    public SharePost(String postTitle, String postDetail, Member postMember, List<String> imageList, LocalDateTime postDeadline) {
        super(postTitle, postDetail, postMember); // 부모 생성자 호출
        this.imageList = imageList;
        this.postDeadline = postDeadline;
    }

    public void addLike(Member member) {
        if (!likeMember.contains(member)) {
            likeMember.add(member);
        }
    }
    public void removeLike(Member member) {
        likeMember.remove(member);
    }
    public int getLike() {
        return likeMember.size();
    }

    public boolean isExpired() {
        return postDeadline != null && LocalDateTime.now().isAfter(postDeadline);
    }
    public void updateSharePost(String postTitle, String postDetail, List<String> imageList, LocalDateTime postDeadline) {
        if (isExpired()) {
            throw new IllegalStateException("마감된 글은 수정할 수 없습니다.");
        }
        super.updatePost(postTitle, postDetail);
        this.imageList = imageList;
        this.postDeadline = postDeadline;
    }
    @Override
    public boolean delete(Member currentMember) {
        return !isExpired() && super.delete(currentMember);
    }
}
