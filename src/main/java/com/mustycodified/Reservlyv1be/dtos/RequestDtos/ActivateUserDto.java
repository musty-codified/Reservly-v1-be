package com.mustycodified.Reservlyv1be.dtos.RequestDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivateUserDto {
    private String email;
    private String emailVerificationToken;
}
