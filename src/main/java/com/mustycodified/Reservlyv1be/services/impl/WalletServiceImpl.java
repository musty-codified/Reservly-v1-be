package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.dtos.responseDtos.WalletResponseDto;
import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.entities.Wallet;
import com.mustycodified.Reservlyv1be.exceptions.UserNotFoundException;
import com.mustycodified.Reservlyv1be.repositories.UserRepository;
import com.mustycodified.Reservlyv1be.repositories.WalletRepository;
import com.mustycodified.Reservlyv1be.services.WalletService;
import com.mustycodified.Reservlyv1be.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final AppUtils appUtil;
    @Override
    public WalletResponseDto updateWallet(String email, BigDecimal amount) {
      User user =  userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        Wallet wallet = walletRepository.findByUserDetails(user).orElse(
                Wallet.builder()
                        .walletUUID(appUtil.generateSerialNumber("w"))
                        .balance(BigDecimal.ZERO)
                        .userDetails(user)
                        .build()
        );
        wallet.setBalance(wallet.getBalance().add(amount));
        wallet.getUpdatedAt();
        return appUtil.getMapper().convertValue(walletRepository.save(wallet), WalletResponseDto.class);
    }
}
