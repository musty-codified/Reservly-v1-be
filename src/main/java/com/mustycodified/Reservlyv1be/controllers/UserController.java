package com.mustycodified.Reservlyv1be.controllers;

import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;
import com.mustycodified.Reservlyv1be.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")  //http://localhost:8080/api/v1/user/signup
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public BaseResponse createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.signUp(createUserRequest);
    }
    @GetMapping("/email-verification") //http://localhost:8080/api/v1/users/email-verification?token=3s4r5tg65
    public BaseResponse verifyEmailToken(@RequestParam (name = "token") String token) {
        return userService.verifyEmailToken(token);

    }
}
