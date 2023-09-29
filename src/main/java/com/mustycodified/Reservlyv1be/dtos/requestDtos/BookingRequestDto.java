package com.mustycodified.Reservlyv1be.dtos.requestDtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    @NotNull (message = "Room Id is mandatory")
    @Schema(example = "1")
    private Long roomId;

    @NotNull (message = "user is mandatory")
    private String userId;

    @NotNull (message = "Room type is mandatory")
    private String roomType;

    @NotNull (message = "Arrival date is mandatory")
    private LocalDate checkInDate;

    @NotNull (message = "Departure date is mandatory")
    private LocalDate checkOutDate;
}
