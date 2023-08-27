package com.mustycodified.Reservlyv1be.controllers;

import com.mustycodified.Reservlyv1be.dtos.requestDtos.ActivateUserDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.ChangePasswordDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.LoginRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.LoginResponseDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.ApiResponse;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.UserResponseDto;
import com.mustycodified.Reservlyv1be.services.UserService;
import com.mustycodified.Reservlyv1be.utils.ResponseManager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final UserService userService;
  private final ResponseManager responseManager;

    @Operation(summary = "Activates a newly created account or an inactive account",
            description = "Once activated, you can then login using the 'login' end point.\n" +
                    "Ensure that you enter the complete verification code sent to your email without any whitespaces, it starts with 'verify...'")
    @PostMapping("/activate-account")
    public ResponseEntity<ApiResponse<UserResponseDto>> activateUser(@RequestBody ActivateUserDto activateUserDto) {
        return ResponseEntity.ok( new ApiResponse<>("User Activated!", true, userService.activateUser(activateUserDto)));
    }

  @Operation(summary = "Resend account activation token or password reset token")
  @PostMapping("/resend-token")
  public ResponseEntity<ApiResponse<String>> resendToken(@Valid @RequestParam String email, @Valid @RequestParam String reason) {
    return ResponseEntity.ok(new ApiResponse<>(userService.sendToken(email, reason),true, email));
  }

  @Operation(summary = "Resets a password after a reset token has been confirmed",
          description = "Before using this endpoint, ensure the forgot password token has been activated")
  @PostMapping("/forgot-password")
  public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody ChangePasswordDto changePasswordDto) {
    return ResponseEntity.ok(new ApiResponse<>(userService.resetPassword(changePasswordDto),true, null));
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<Object>> login(@RequestBody LoginRequestDto LoginRequest) {
    LoginResponseDto loginResponseDto = userService.login(LoginRequest);
    return responseManager.success(loginResponseDto);
  }

  @Operation(summary = "Blacklists the users token")
  @PostMapping("/logout")
  public ResponseEntity<ApiResponse<Object>> logout(@RequestHeader("Authorization") String token) {
    return responseManager.success(userService.logout(token));
  }
}
