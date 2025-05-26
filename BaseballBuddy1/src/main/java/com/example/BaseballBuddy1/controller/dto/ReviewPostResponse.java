package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.post.ReviewPost;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewPostResponse {
    private String postTitle;
    private String postDetail;
    private Stadium stadium;
    private FilterTag filter;
    private List<String> imageList;

    public ReviewPostResponse(String postTitle, String postDetail, Stadium stadium, FilterTag filter, List<String> imageList) {
        this.postTitle = postTitle;
        this.postDetail = postDetail;
        this.stadium = stadium;
        this.filter = filter;
        this.imageList = imageList;
    }
    public ReviewPostResponse(ReviewPost reviewPost) {
        this.postTitle = reviewPost.getPostTitle();
        this.postDetail = reviewPost.getPostDetail();
        this.stadium = reviewPost.getStadium();
        this.filter = reviewPost.getFilter();
        this.imageList = reviewPost.getImageList();
    }
    public static ReviewPostResponse from(ReviewPost post) {
        return new ReviewPostResponse(
                post.getPostID(),
                post.getPostTitle(),
                post.getPostDetail(),
                post.getStadium(),
                post.getFilter(),
                post.getImageList(),
                post.getPostDate()
        );
    }

}
