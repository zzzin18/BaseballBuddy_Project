package com.example.BaseballBuddy1.controller.dto;

import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.Stadium;

import java.util.List;

public class ReviewPostRequest {
    private String postTitle;
    private String postDetail;
    private Stadium stadium;
    private FilterTag filter;
    private List<String> image;

    public String getPostTitle() {
        return postTitle;
    }
    public String getPostDetail() {
        return postDetail;
    }
    public Stadium getStadium() {
        return stadium;
    }
    public FilterTag getFilter() {
        return filter;
    }
    public List<String> getImage() {
        return image;
    }
}

