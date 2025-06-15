package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.domain.Stadium;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stadiums")
public class StadiumController {

    @GetMapping
    public ResponseEntity<List<String>> getAllStadiums() {
        List<String> stadiumNames = Arrays.stream(Stadium.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(stadiumNames);
    }
}

