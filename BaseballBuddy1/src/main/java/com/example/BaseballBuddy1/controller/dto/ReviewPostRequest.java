package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.Stadium;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ReviewPostRequest {
    private String postTitle;
    private String postDetail;
    private Stadium stadium;
    private FilterTag filter;
    //private List<String> imageList;
    private String imageUrl;
}

