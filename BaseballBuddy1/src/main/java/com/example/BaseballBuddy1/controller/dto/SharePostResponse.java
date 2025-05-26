package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.post.SharePost;
import com.example.BaseballBuddy1.domain.post.SharePostRepository;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@Getter
@Builder
public class SharePostResponse {
    private String ID;
    private String postTitle;
    private String postDetail;
    private String postWriter;
    private LocalDateTime postDate;
    private List<String> imageList;
    private LocalDateTime postDeadline;
    private int likeCount;
    private boolean expired;
    private boolean likedByCurrentUser;

    public static SharePostResponse from(SharePost post) {
        return new SharePostResponse(
                post.getPostID(),
                post.getPostTitle(),
                post.getPostDetail(),
                post.getPostMember().getMemberID(),
                post.getPostDate(),
                post.getImageList(),
                post.getPostDeadline(),
                post.getLike(),
                post.getPostDeadline().isBefore(LocalDateTime.now()),
                false
        );
    }

    public static SharePostResponse from(SharePost post, Member member) {
        boolean liked = post.getLikeMember().contains(member);
        return new SharePostResponse(
                post.getPostID(),
                post.getPostTitle(),
                post.getPostDetail(),
                post.getPostMember().getMemberID(),
                post.getPostDate(),
                post.getImageList(),
                post.getPostDeadline(),
                post.getLike(),
                post.getPostDeadline().isBefore(LocalDateTime.now()),
                liked
        );
    }


    public static SharePostResponse toResponse(SharePost post, Member member) {
        boolean liked = post.getLikeMember().contains(member);

        return new SharePostResponse(
                post.getPostID(),
                post.getPostTitle(),
                post.getPostDetail(),
                post.getPostMember().getMemberID(),  // 또는 getName() 등 작성자 표시용
                post.getPostDate(),
                post.getImageList(),
                post.getPostDeadline(),
                post.getLike(),  // 좋아요 수
                post.getPostDeadline().isBefore(LocalDateTime.now()), // 만료 여부
                liked
        );
    }
}