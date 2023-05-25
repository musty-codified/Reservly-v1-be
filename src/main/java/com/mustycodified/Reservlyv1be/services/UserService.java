package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.dtos.requests.ForgotPasswordRequest;
import com.mustycodified.Reservlyv1be.dtos.requests.PasswordResetRequest;
import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;
import org.springframework.security.core.userdetails.UserDetailsService;


//Interface Segregation Principle
public interface UserService extends UserDetailsService {
    BaseResponse signUp(CreateUserRequest createUserRequest);
    BaseResponse verifyEmailToken( String token);
   BaseResponse forgotPasswordRequest(ForgotPasswordRequest resetRequest);
   BaseResponse resetPassword(PasswordResetRequest passwordResetRequest);


}
