package com.mustycodified.Reservlyv1be.dtos.requestDtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDto {

    @NotBlank(message = "Room number is mandatory")
    @Schema(example = "RM-01")
    private String roomNumber;

    @NotBlank(message = "Room type is mandatory")
    @Schema(example = "single")
    private String roomType;

    @NotBlank(message = "Room description is mandatory")
    @Schema(example = "Deluxe single room")
    private String roomDescription;


}
