package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.requestDtos.BookingRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.BookingResponseDto;

public interface BookingService {
    BookingResponseDto createBooking(BookingRequestDto bookingDto);
}
