package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.post.SharePost;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SharePostRequest {
    private String postTitle;
    private String postDetail;
    private List<String> imageList;
    private LocalDateTime postDeadline;

    public SharePostRequest() {
    }

    public SharePostRequest(String postTitle, String postDetail, List<String> imageList, LocalDateTime postDeadline) {
        this.postTitle = postTitle;
        this.postDetail = postDetail;
        this.imageList = imageList;
        this.postDeadline = postDeadline;
    }

    private SharePostResponse toResponse(SharePost post, Member member) {
        boolean isMine = (member != null) && post.getPostMember().equals(member);
        boolean isLiked = (member != null) && post.getLikeMember().contains(member);

        return new SharePostResponse(
                post.getPostID(),
                post.getPostTitle(),
                post.getPostDetail(),
                post.getPostMember().getNickname(),
                post.getPostDate(),
                post.getImageList(),
                post.getPostDeadline(),
                post.getLike(),
                post.isExpired(),
                isLiked
        );
    }

}