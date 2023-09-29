package com.mustycodified.Reservlyv1be.dtos.requestDtos;


import com.mustycodified.Reservlyv1be.enums.RoomType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDto {

    @NotBlank(message = "Room number is mandatory")
    @Schema(example = "RM-01")
    private String roomNumber;

    @NotBlank(message = "Room type is mandatory")
    @Schema(example = "single")
    private RoomType roomType;

    @NotBlank(message = "Room description is mandatory")
    @Schema(example = "Deluxe single room with AC")
    private String roomDescription;

    @DecimalMin(value="5000.0", message="Field Price cannot be blank")
    private BigDecimal pricePerNight;

}
