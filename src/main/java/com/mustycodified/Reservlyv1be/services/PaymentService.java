package com.mustycodified.Reservlyv1be.services;


import com.mustycodified.Reservlyv1be.dtos.paystack.TransactionInitRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.ApiResponse;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.TransactionResponseDto;

public interface PaymentService {
    ApiResponse getPaymentLink(TransactionInitRequestDto transactionDto);

    ApiResponse<TransactionResponseDto> verifyTransaction(String payment_reference);
}
