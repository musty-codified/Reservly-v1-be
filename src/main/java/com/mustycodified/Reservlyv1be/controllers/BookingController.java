package com.mustycodified.Reservlyv1be.controllers;


import com.mustycodified.Reservlyv1be.dtos.requestDtos.BookingRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.BookingResponseDto;
import com.mustycodified.Reservlyv1be.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/add")
    public ResponseEntity<BookingResponseDto>createBooking(@RequestBody @Valid BookingRequestDto bookingRequestDto){
        BookingResponseDto bookingResponseDto =  bookingService.createBooking(bookingRequestDto);
        return new ResponseEntity<>(bookingResponseDto, HttpStatus.CREATED);


    }
}
