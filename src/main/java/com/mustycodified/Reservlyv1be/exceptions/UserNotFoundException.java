package com.mustycodified.Reservlyv1be.exceptions;

public class UserNotFoundException extends RuntimeException{

    private String debugMessage;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, String debugMessage) {
        super(message);
        this.debugMessage = debugMessage;
    }
}
