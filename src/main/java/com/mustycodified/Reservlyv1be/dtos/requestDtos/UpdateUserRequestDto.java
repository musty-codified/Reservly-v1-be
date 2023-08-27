package com.mustycodified.Reservlyv1be.dtos.requestDtos;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UpdateUserRequestDto {
    @NotNull(message = "Please select your email")
    private String email;

    @NotNull(message = "Please Provide userName")
    private String username;

}
