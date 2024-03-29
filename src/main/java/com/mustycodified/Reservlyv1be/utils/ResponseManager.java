package com.mustycodified.Reservlyv1be.utils;

import com.mustycodified.Reservlyv1be.dtos.responseDtos.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class ResponseManager {
    public ResponseEntity<ApiResponse<Object>> success(Object payload){
        return ResponseEntity.ok().body(
                new ApiResponse<>("Request successful", true, payload)
        );
    }

    public ResponseEntity<ApiResponse<Object>> success(String message, Object payload) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(message, true, payload));
    }
}
