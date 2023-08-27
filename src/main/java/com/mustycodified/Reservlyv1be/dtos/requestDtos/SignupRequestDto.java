package com.mustycodified.Reservlyv1be.dtos.requestDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "Email address is mandatory")
    @Schema(example = "ilemonamustapha@gmail.com")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Schema(example = "1994")
    private String password;

    @NotBlank(message = "Username is mandatory")
    @Schema(example = "Mustapha")
    private String username;

    @NotNull
    @Schema(example = "Ilara Road, Ilisan Remo")
    private String address;

    @NotNull @Schema(example = "08166099828")
    private String phoneNumber;


}
