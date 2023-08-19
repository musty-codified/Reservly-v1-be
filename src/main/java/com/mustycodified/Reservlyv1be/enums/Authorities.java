package com.mustycodified.Reservlyv1be.enums;

public enum Authorities {

    USER_READ("user:read"),
    USER_EDIT("user:write"),
    USER_DELETE("user:delete");
    private final String authority;

    Authorities(String authority) {
        this.authority = authority;
    }
}
