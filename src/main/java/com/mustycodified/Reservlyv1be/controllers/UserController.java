package com.mustycodified.Reservlyv1be.controllers;

import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.dtos.requests.ForgotPasswordRequest;
import com.mustycodified.Reservlyv1be.dtos.requests.PasswordResetRequest;
import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;
import com.mustycodified.Reservlyv1be.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup") //http://localhost:8080/api/v1/users/signup
    public BaseResponse createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.signUp(createUserRequest);
    }
    @GetMapping("/email-verification") //http://localhost:8080/api/v1/users/email-verification?token=3s4r5tg65
    public BaseResponse verifyEmailToken(@RequestParam (name = "token") String token) {
        return userService.verifyEmailToken(token);
    }
    @PostMapping("/forgot-password") //http://localhost:8080/api/v1/users/forgot-password
    public BaseResponse forgotPasswordRequest(@RequestBody @Valid ForgotPasswordRequest resetRequest){
        return userService.forgotPasswordRequest(resetRequest);
    }
    @PostMapping("/reset-password") //http://localhost:8080/api/v1/users/reset-password?token=3s4r5tg65
    public BaseResponse resetPassword(@RequestBody PasswordResetRequest passwordResetRequest){
        return userService.resetPassword(passwordResetRequest);
    }

//    @GetMapping
//    public BaseResponse viewProfile(){
//        return userService.viewProfile();
//    }

}
