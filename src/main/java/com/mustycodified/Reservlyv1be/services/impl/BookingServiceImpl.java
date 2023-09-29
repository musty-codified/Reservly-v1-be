package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.dtos.requestDtos.BookingRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.BookingResponseDto;
import com.mustycodified.Reservlyv1be.entities.Booking;
import com.mustycodified.Reservlyv1be.entities.Room;
import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.enums.TransactionStatus;
import com.mustycodified.Reservlyv1be.exceptions.RoomServiceException;
import com.mustycodified.Reservlyv1be.exceptions.UserNotFoundException;
import com.mustycodified.Reservlyv1be.repositories.BookingRepository;
import com.mustycodified.Reservlyv1be.repositories.RoomRepository;
import com.mustycodified.Reservlyv1be.repositories.UserRepository;
import com.mustycodified.Reservlyv1be.services.BookingService;
import com.mustycodified.Reservlyv1be.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingDto) {

        //Validate booking
        User user = userRepository
                .findByUserId(bookingDto.getUserId()).orElseThrow(()-> new UserNotFoundException("User not found"));

       Room room = roomRepository.findById(bookingDto.getRoomId())
                .orElseThrow(()-> new RoomServiceException("Room not found"));

       if (!room.isAvailable()){
           throw new RoomServiceException("Selected room is not available for the specified dates");
       }

      BigDecimal totalPrice = calculateTotalPrice(room, bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
        //Remember booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCheckInDate(bookingDto.getCheckInDate());
        booking.setCheckOutDate(bookingDto.getCheckOutDate());
        booking.setTotalPrice(totalPrice);
        booking.setTransactionStatus(TransactionStatus.PENDING.name());
        booking.setRoom(room);

        Booking storedBooking = bookingRepository.save(booking);
        room.setAvailable(false);
        roomRepository.save(room);

        //Handle Payment
        return Mapper.toBookingDto(storedBooking);
    }

    private BigDecimal calculateTotalPrice(Room room, LocalDate checkInDate, LocalDate checkOutDate) {

        long numberOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal roomRate = room.getPricePerNight();

        BigDecimal totalPrice = roomRate.multiply(BigDecimal.valueOf(numberOfDays));

        return totalPrice;
    }
}
