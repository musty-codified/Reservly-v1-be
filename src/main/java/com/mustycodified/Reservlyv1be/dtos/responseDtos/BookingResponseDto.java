package com.mustycodified.Reservlyv1be.dtos.responseDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDto {

    private Long bookingId;

    private String userId;

    private Long roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private BigDecimal totalPrice;

    private String transactionStatus;
}
