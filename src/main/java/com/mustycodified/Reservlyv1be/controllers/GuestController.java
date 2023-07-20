package com.mustycodified.Reservlyv1be.controllers;


import com.mustycodified.Reservlyv1be.dtos.GuestSignupRequestDto;
import com.mustycodified.Reservlyv1be.services.GuestService;
import com.mustycodified.Reservlyv1be.utils.ApiResponse;
import com.mustycodified.Reservlyv1be.utils.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class GuestController {

    private final ResponseManager responseManager;
    private final GuestService guestService;

    @PostMapping("guest/signup")
    public ResponseEntity<ApiResponse> createGuest(@RequestBody GuestSignupRequestDto signupRequestDto){

        guestService.createGuest(signupRequestDto);
        return new ResponseEntity<>(responseManager.success("Successful, Verify your email"), HttpStatus.CREATED);

    }
}
