package com.mustycodified.Reservlyv1be.dtos.responseDtos;


import com.mustycodified.Reservlyv1be.enums.RoomType;
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

    private RoomType roomType;

    private String description;

}
