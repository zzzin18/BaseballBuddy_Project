package com.example.BaseballBuddy1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filters")
public class filterController {
    @GetMapping
    public Map<String, List<String>> getFilters() {
        Map<String, List<String>> filters = new HashMap<>();

        filters.put("Stadium", List.of("INCHEON", "JAMSIL", "GOCHUK", "SUWON", "DAEJEON", "DAEGU", "GWANGJU", "BUSAN", "CHANGWON", "ETC"));
        filters.put("SeatTypes", List.of("INFIELD", "OUTFIELD"));
        filters.put("CheerStyle", List.of("CHEER", "COMMUNICATION"));
        filters.put("GroupType", List.of("ALONE", "GROUP"));

        return filters;
    }
}
