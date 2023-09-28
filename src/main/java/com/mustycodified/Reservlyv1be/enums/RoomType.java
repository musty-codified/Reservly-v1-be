package com.mustycodified.Reservlyv1be.enums;

public enum RoomType {
    ROOM_TYPE_SINGLE ("single"),
    ROOM_TYPE_DOUBLE ("double"),
    ROOM_TYPE_SUITE ("suite");
    private final String room;
    RoomType(String room) {
        this.room = room;
    }
    public String getRoom(){
        return room;
    }
}
