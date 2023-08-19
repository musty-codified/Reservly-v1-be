package com.mustycodified.Reservlyv1be.utils;

import com.mustycodified.Reservlyv1be.dtos.ResponseDtos.UserResponseDto;
import com.mustycodified.Reservlyv1be.entities.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static UserResponseDto toUserDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }

}
