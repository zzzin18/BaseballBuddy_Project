package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.member.Member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter @Setter
public class ReviewPost extends Post {
    @Enumerated(EnumType.STRING)
    private Stadium stadium;
    @Enumerated(EnumType.STRING)
    private FilterTag filter;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<FilterTag> filterTags = new ArrayList<>();
    @ElementCollection

    private List<String> imageList = new ArrayList<>();


    protected ReviewPost() { super(); }

    public ReviewPost(String postTitle, String postDetail, Member postMember, Stadium stadium, FilterTag filter) {
        super(postTitle, postDetail, postMember);
        this.postMember = postMember;
        this.stadium = stadium;
        this.filter = filter;
    }

    public void updateReviewPost(String postTitle, String postDetail, Stadium stadium, FilterTag filter, List<String> imageList) {
        this.setPostTitle(postTitle);
        this.setPostDetail(postDetail);
        this.stadium = stadium;
        this.filter = filter;
        if (imageList != null && !imageList.isEmpty()) {
            this.imageList.clear();
            this.imageList.addAll(imageList);
        }
    }

    @Override
    public boolean canModify(Member currentMember) {
        return true;
    }
    @Override
    public void updatePost(String postTitle, String postDetail) {
        updateReviewPost(postTitle, postDetail, this.stadium, this.filter, null);
    }
    @Override
    public boolean delete(Member currentMember) {
        return true;
    }

    public void addLike(Member member) {
        likedMembers.add(member);
    }

    public void removeLike(Member member) {
        likedMembers.remove(member);
    }
    public int getLike() {
        return likedMembers.size();
    }
    public String getNickname() {
        return postMember.getNickname();
    }

    @Getter
    @ManyToMany
    @JoinTable(
            name = "reviewpost_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> likedMembers = new HashSet<>();

}

