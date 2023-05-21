package com.mustycodified.Reservlyv1be.enums;

public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double"),
    TWIN("Twin"),
    TRIPLE("Triple"),
    SUITE("Suite");
    private final String value;
     RoomType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
