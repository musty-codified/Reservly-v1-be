package com.mustycodified.Reservlyv1be.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorMessage {

    private Date timeStamp;
    private String message;
    private String debugMessage;

    public ErrorMessage(Date timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
