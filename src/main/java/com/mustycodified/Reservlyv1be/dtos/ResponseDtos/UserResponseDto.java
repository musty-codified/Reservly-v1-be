package com.mustycodified.Reservlyv1be.dtos.ResponseDtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private String userId;
    private String email;
    private String username;
    private String address;
    private String phoneNumber;
    private Date lastLoginDate;
    private String token;
}
