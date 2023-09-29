package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.dtos.requestDtos.RoomRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.RoomResponseDto;
import com.mustycodified.Reservlyv1be.entities.Room;
import com.mustycodified.Reservlyv1be.enums.RoomType;
import com.mustycodified.Reservlyv1be.exceptions.RecordAlreadyExistException;
import com.mustycodified.Reservlyv1be.repositories.RoomRepository;
import com.mustycodified.Reservlyv1be.services.RoomService;
import com.mustycodified.Reservlyv1be.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    public RoomResponseDto createRoom(RoomRequestDto roomRequestDto) {
        //Validate room
      if(roomRepository.existsByRoomNumber(roomRequestDto.getRoomNumber())){
          throw new RecordAlreadyExistException("Room already exist");
      }

        //Remember room
        Room room = new Room();
        room.setRoomNumber(roomRequestDto.getRoomNumber());
        room.setRoomType(roomRequestDto.getRoomType());
        room.setAvailable(true);
        room.setDescription(roomRequestDto.getRoomDescription());
        room.setPricePerNight(roomRequestDto.getPricePerNight());
        Room storedRoom = roomRepository.save(room);
        return Mapper.toRoomDto(storedRoom);
    }
}
