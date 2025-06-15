package com.example.BaseballBuddy1.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Stadium {
    INCHEON("인천"), JAMSHIL("잠실"), GOCHEOK("고척"), SUWON("수원"), DAEJEON("대전"),
    DAEGU("대구"), GWANGJU("광주"), BUSAN("부산"), CHANGWON("창원"), ETC("기타");

    private final String displayName;

    Stadium(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static Stadium from(String value) {
        for (Stadium s : Stadium.values()) {
            if (s.name().equalsIgnoreCase(value) || s.displayName.equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown stadium: " + value);
    }
}
