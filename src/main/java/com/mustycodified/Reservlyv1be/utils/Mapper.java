package com.mustycodified.Reservlyv1be.utils;

import com.mustycodified.Reservlyv1be.dtos.responseDtos.BookingResponseDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.RoomResponseDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.UserResponseDto;
import com.mustycodified.Reservlyv1be.entities.Booking;
import com.mustycodified.Reservlyv1be.entities.Room;
import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.enums.RoomType;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static UserResponseDto toUserDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }

    public static RoomResponseDto toRoomDto(Room room){
        return RoomResponseDto.builder()
                .roomId(room.getId())
                .description(room.getDescription())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType())
                .build();
    }

    public static BookingResponseDto toBookingDto(Booking booking){
       return BookingResponseDto.builder()
                .bookingId(booking.getId())
                .roomId(booking.getRoom().getId())
                .checkOutDate(booking.getCheckOutDate())
                .checkInDate(booking.getCheckInDate())
                .totalPrice(booking.getTotalPrice())
                .transactionStatus(booking.getTransactionStatus())
                .userId(booking.getUser().getUserId())
                .build();

    }
}
