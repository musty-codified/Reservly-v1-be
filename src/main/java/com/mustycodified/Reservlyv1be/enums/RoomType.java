package com.mustycodified.Reservlyv1be.enums;

public enum RoomType {
    ROOM_TYPE_SINGLE ("room:single"),
    ROOM_TYPE_DOUBLE ("room:double"),
    ROOM_TYPE_SUITE ("room:suite");
    private final String room;
    RoomType(String room) {
        this.room = room;
    }
    public String getRoom(){
        return room;
    }
}
