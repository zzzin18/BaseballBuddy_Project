package com.example.BaseballBuddy1.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterTag{
    FIRSTBASE("1루"), THIRDBASE("3루"), OUTFIELD("외야"),
    FOOD("음식"), FACILITY("공용시설"), PARKING("주차"),
    GOODS("굿즈샵"), ETC("기타");

    private final String displayName;

    FilterTag(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}

