package com.example.BaseballBuddy1.controller;

import com.example.BaseballBuddy1.domain.FilterTag;
import com.example.BaseballBuddy1.domain.Stadium;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/stadium")
    public ResponseEntity<List<String>> getStadiums() {
        List<String> stadiums = Arrays.stream(Stadium.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(stadiums);
    }

    @GetMapping("/filterTag")
    public ResponseEntity<List<String>> getFilterTags() {
        List<String> filters = Arrays.stream(FilterTag.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(filters);
    }
}
