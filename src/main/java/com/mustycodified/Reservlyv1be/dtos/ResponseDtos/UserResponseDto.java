package com.mustycodified.Reservlyv1be.dtos.ResponseDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {

    private String userId;
    private String email;
    private String userName;
    private Boolean verificationStatus;
}
