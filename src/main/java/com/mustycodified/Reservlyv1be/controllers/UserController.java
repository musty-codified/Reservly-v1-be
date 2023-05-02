package com.mustycodified.Reservlyv1be.controllers;

import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;
import com.mustycodified.Reservlyv1be.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")  //http://localhost:8080/api/v1/user/signup
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/user/signup")
    public BaseResponse createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.signUp(createUserRequest);
    }
}
