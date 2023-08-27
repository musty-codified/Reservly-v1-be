package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.responseDtos.WalletResponseDto;

import java.math.BigDecimal;

public interface WalletService {
    WalletResponseDto updateWallet(String email, BigDecimal amount);
}
