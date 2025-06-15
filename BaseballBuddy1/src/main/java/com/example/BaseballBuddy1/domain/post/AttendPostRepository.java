package com.example.BaseballBuddy1.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;

public interface AttendPostRepository extends JpaRepository<AttendPost, String> {
    List<AttendPost> findAllByOrderByCreatedAtDesc();
    List<AttendPost> findByPostMemberMemberId(String memberId);
    List<AttendPost> findByAttendDate(LocalDate date);
    List<AttendPost> findByAttendDateBetween(LocalDateTime start, LocalDateTime end);


}
