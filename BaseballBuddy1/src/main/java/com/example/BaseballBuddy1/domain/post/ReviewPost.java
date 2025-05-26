package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.member.Member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ReviewPost extends Post {
    @Enumerated(EnumType.STRING)
    private Stadium stadium;
    @Enumerated(EnumType.STRING)
    private FilterTag filter;
    @ElementCollection
    private List<String> imageList = new ArrayList<>();

    public ReviewPost(String postTitle, String postDetail, Member postMember, Stadium stadium, FilterTag filter) {
        super(postTitle, postDetail, postMember);
        this.stadium = stadium;
        this.filter = filter;
    }

    public void updateReviewPost(String postTitle, String postDetail, Stadium stadium, FilterTag filter, List<String> imageList) {
        super.setPostTitle(postTitle);
        super.setPostDetail(postDetail);
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
}

