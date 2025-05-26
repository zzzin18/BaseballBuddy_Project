package com.example.BaseballBuddy1.service;

import com.example.BaseballBuddy1.controller.dto.AttendPostRequest;
import com.example.BaseballBuddy1.domain.member.Member;
import com.example.BaseballBuddy1.domain.post.AttendPost;
import com.example.BaseballBuddy1.domain.post.AttendPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendPostService {

    private final AttendPostRepository attendPostRepository;

    private boolean isInvalidRequest(AttendPostRequest request) {
        return request.getPostTitle() == null || request.getPostTitle().trim().isEmpty()
                || request.getPostDetail() == null || request.getPostDetail().trim().isEmpty()
                || request.getPostDeadline() == null;
    }

    public void createPost(AttendPostRequest request) {
        if (isInvalidRequest(request)) {
            throw new IllegalArgumentException("모두 입력하세요.");
        }
        // 임시로 작성자 Member null로 둡니다 (로그인 시스템 연동 시 Member 주입 필요)
        AttendPost post = new AttendPost(
                request.getPostTitle(),
                request.getPostDetail(),
                request.getPostDeadline(),
                null  // 실제 구현 시 SecurityContext 등에서 Member 가져오기
        );
        attendPostRepository.save(post);
    }

    public List<AttendPost> getAllPosts() {
        return attendPostRepository.findAllByOrderByCreatedAtDesc();
    }

    public void updatePost(Long id, AttendPostRequest request) {
        if (isInvalidRequest(request)) {
            throw new IllegalArgumentException("모두 입력하세요.");
        }
        AttendPost post = attendPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        post.updateAttendPost(request.getPostTitle(), request.getPostDetail(), request.getPostDeadline());
    }

    public void deletePost(Long id) {
        AttendPost post = attendPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        if (!post.isExpired()) {
            attendPostRepository.delete(post);
        } else {
            throw new IllegalStateException("마감된 글은 삭제할 수 없습니다.");
        }
    }
}
