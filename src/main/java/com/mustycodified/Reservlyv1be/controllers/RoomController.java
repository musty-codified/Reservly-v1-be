package com.mustycodified.Reservlyv1be.controllers;


import com.mustycodified.Reservlyv1be.dtos.requestDtos.RoomRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.RoomResponseDto;
import com.mustycodified.Reservlyv1be.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<RoomResponseDto> createRoom(@RequestBody RoomRequestDto roomRequestDto){
       RoomResponseDto responseDto = roomService.createRoom(roomRequestDto);
       return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
