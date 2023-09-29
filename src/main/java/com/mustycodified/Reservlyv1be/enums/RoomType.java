package com.mustycodified.Reservlyv1be.enums;

public enum RoomType {
    SINGLE ("single"),
    DOUBLE ("double"),
    SUITE ("suite"),
    QUAD ("quad"),
    KING ("king");
    private final String room;
    RoomType(String room) {
        this.room = room;
    }
    public String getRoom(){
        return room;
    }
}
