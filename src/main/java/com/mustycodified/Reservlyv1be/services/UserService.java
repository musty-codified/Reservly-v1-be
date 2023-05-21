package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;
import org.springframework.security.core.userdetails.UserDetailsService;


//interface segregation principle
public interface UserService extends UserDetailsService {
    BaseResponse signUp(CreateUserRequest createUserRequest);
    BaseResponse verifyEmailToken( String token);
}
