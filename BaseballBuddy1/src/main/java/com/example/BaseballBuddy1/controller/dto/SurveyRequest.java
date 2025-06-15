package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.CheerStyle;
import com.example.BaseballBuddy1.domain.SeatType;
import com.example.BaseballBuddy1.domain.Stadium;
import com.example.BaseballBuddy1.domain.GroupType;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SurveyRequest {
    private LocalDate date;
    private Stadium stadium;
    private SeatType seatType;
    private CheerStyle cheerStyle;
    private GroupType groupType;
}