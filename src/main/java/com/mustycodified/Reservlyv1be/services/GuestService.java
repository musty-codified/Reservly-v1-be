package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.GuestSignupRequestDto;
import com.mustycodified.Reservlyv1be.dtos.SignupResponseDto;

public interface GuestService {
    SignupResponseDto createGuest(GuestSignupRequestDto signupRequestDto);

}
