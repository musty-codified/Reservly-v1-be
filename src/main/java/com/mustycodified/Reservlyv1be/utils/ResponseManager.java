package com.mustycodified.Reservlyv1be.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResponseManager <T>{

    public ApiResponse success(T data){
        return new ApiResponse<T>("Message Successful", true,  data);
    }

    public ApiResponse error(String errorMessage){
        return new ApiResponse<T>(errorMessage, true, null);
    }
}
