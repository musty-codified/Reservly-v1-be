package com.mustycodified.Reservlyv1be.dtos;


import com.mustycodified.Reservlyv1be.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String date_of_birth;
    private String phoneNumber;
    private Boolean verificationStatus;
    private String address;
}
