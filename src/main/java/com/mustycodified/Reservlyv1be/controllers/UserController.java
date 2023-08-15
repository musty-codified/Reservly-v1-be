package com.mustycodified.Reservlyv1be.controllers;


import com.mustycodified.Reservlyv1be.dtos.RequestDtos.GuestUpdateRequestDto;
import com.mustycodified.Reservlyv1be.dtos.RequestDtos.GuestSignupRequestDto;
import com.mustycodified.Reservlyv1be.dtos.ResponseDtos.UpdateResponseDto;
import com.mustycodified.Reservlyv1be.services.GuestService;
import com.mustycodified.Reservlyv1be.dtos.ResponseDtos.ApiResponse;
import com.mustycodified.Reservlyv1be.utils.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/guest/view-details")
    public ResponseEntity<UpdateResponseDto> viewProfile(){
        return new ResponseEntity<>(guestService.viewDetails(), HttpStatus.OK);
    }
}
