package com.mustycodified.Reservlyv1be.dtos.responseDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponseDto {
    private long roomId;

    private String roomNumber;

    private String roomType;

    private String description;


}
