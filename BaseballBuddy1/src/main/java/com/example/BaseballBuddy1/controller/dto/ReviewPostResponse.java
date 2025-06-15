package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.post.ReviewPost;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReviewPostResponse {
    private String postID;
    private String postTitle;
    private String postDetail;
    private String nickname;
    private Stadium stadium;
    private List<FilterTag> filterTags;
   // private List<String> imageList;
    private LocalDateTime postDate;



    public ReviewPostResponse(String postID, String postTitle, String postDetail, String nickname, Stadium stadium ,List<FilterTag> filtertags /*List<String> imageList*/, LocalDateTime postDate) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postDetail = postDetail;
        this.nickname = nickname;
        this.stadium = stadium;
        this.filterTags = filtertags;
        //this.imageList = imageList;
        this.postDate = postDate;
    }
    public ReviewPostResponse(ReviewPost reviewPost) {
        this.postID = reviewPost.getPostId(); // 추가
        this.postTitle = reviewPost.getPostTitle();
        this.postDetail = reviewPost.getPostDetail();
        this.nickname = reviewPost.getPostMember().getNickname(); // 추가
        this.stadium = reviewPost.getStadium();
        this.filterTags = reviewPost.getFilterTags();
        this.postDate = reviewPost.getPostDate();
    }

    public static ReviewPostResponse from(ReviewPost post) {
        return new ReviewPostResponse(
                post.getPostId(),
                post.getPostTitle(),
                post.getPostDetail(),
                post.getNickname(),
                post.getStadium(),
                post.getFilterTags(),
               // post.getImageList(),
                post.getPostDate()
        );
    }

    public static ReviewPostResponse fromEntity(ReviewPost post) {
        return ReviewPostResponse.builder()
                .postID(post.getPostId())
                .postTitle(post.getPostTitle())
                .postDetail(post.getPostDetail())
                .nickname(post.getPostMember().getNickname())
                .stadium(post.getStadium())
                .filterTags(post.getFilterTags())
                //.imageList(post.getImageList())
                .postDate(post.getPostDate())
                .build();
    }


}
