package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.controller.dto.AttendPostResponse;
import com.example.BaseballBuddy1.controller.dto.RecommendRequest;
import com.example.BaseballBuddy1.service.AttendPostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    private final AttendPostService attendPostService;

    public RecommendController(AttendPostService attendPostService) {
        this.attendPostService = attendPostService;
    }

    /*
    @PostMapping("/result")
    public List<AttendPostResponse> getRecommendation(@RequestBody RecommendRequest request) {
        return attendPostService.findRecommendedPosts(request);
    }
    */
}

