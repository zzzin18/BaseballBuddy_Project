package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.controller.dto.SharePostRequest;
import com.example.BaseballBuddy1.controller.dto.SharePostResponse;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.post.SharePost;
import com.example.BaseballBuddy1.domain.post.SharePostRepository;
import com.example.BaseballBuddy1.domain.member.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SharePostService {

    private final SharePostRepository sharePostRepository;
    private final MemberRepository memberRepository;

    public String createPost(SharePostRequest request, String memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원 없음"));
        SharePost post = new SharePost(request.getPostTitle(), request.getPostDetail(), member, request.getImageList(), request.getPostDeadline());

        return sharePostRepository.save(post).getPostID();
    }

    public SharePostResponse getPost(String ID, String memberID) {
        SharePost post = sharePostRepository.findById(ID)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        Member member = memberRepository.findById(memberID)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));
        return toResponse(post,member);
    }

    private SharePostResponse toResponse(SharePost post, Member member) {
        boolean isMember = post.getPostMember().equals(member);
        boolean isLiked = post.getLikeMember().contains(member);

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

    public List<SharePostResponse> getAllPosts(String memberId) {
        final Member member = (memberId != null && !memberId.isBlank())
                ? memberRepository.findById(memberId).orElse(null)
                : null;

        return sharePostRepository.findAllByOrderByPostDateDesc().stream()
                .map(post -> toResponse(post, member))
                .toList();
    }



    public void updatePost(String id, SharePostRequest request, String memberId) {
        SharePost post = sharePostRepository.findById(id).get();
        if (!post.getPostMember().getMemberID().equals(memberId)) {
            throw new IllegalStateException("작성자만 수정 가능");
        }

        post.updateSharePost(request.getPostTitle(), request.getPostDetail(), request.getImageList(), request.getPostDeadline());
    }

    public void deletePost(String id, String memberId) {
        SharePost post = sharePostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        if (!post.delete(post.getPostMember())) {
            throw new IllegalStateException("마감된 글은 삭제할 수 없습니다.");
        }
        sharePostRepository.delete(post);
    }

    public void likePost(String postId, String memberId) {
        SharePost post = sharePostRepository.findById(postId).get();
        Member member = memberRepository.findById(memberId).get();
        post.addLike(member);
    }
    public void unlikePost(String postId, String memberId) {
        SharePost post = sharePostRepository.findById(postId).get();
        Member member = memberRepository.findById(memberId).get();
        post.removeLike(member);
    }

    @Transactional
    public void toggleLike(String postId, String memberId) {
        SharePost post = sharePostRepository.findById(postId).get();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("로그인이 필요한 기능입니다."));

        if (post.getLikeMember().contains(member)) {
            post.removeLike(member);
        } else {
            post.addLike(member);
        }
    }
}

