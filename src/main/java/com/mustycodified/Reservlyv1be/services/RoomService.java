package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.requestDtos.RoomRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.RoomResponseDto;

public interface RoomService{
    RoomResponseDto createRoom(RoomRequestDto roomRequestDto);
}
