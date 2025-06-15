package com.example.BaseballBuddy1.domain.post;

import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.SeatType;
import com.example.BaseballBuddy1.domain.CheerStyle;
import com.example.BaseballBuddy1.domain.GroupType;
import com.example.BaseballBuddy1.domain.member.Member;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class AttendPost extends Post{
    @Column(name = "attend_date")
    private LocalDateTime attendDate;

    private Stadium stadium;
    private CheerStyle cheerStyle;
    private SeatType seatType;
    private GroupType groupType;

    protected AttendPost() {
    }

    public AttendPost(String postTitle, String postDetail, Member postMember, LocalDateTime attendDate) {
        super(postTitle, postDetail, postMember);
        this.attendDate = attendDate;
    }
/*
    public boolean isExpired() {
        return LocalDateTime;
    }*/

    public void updateAttendPost(String postTitle, String postDetail) {
        super.updatePost(postTitle, postDetail);

    }

    public boolean canModify(Member currentMember) {
        return super.canModify(currentMember);
    }


    public String getNickname() {
        return getPostMember().getNickname();
    }

}

