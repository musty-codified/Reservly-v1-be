package com.mustycodified.Reservlyv1be.controllers;


import com.mustycodified.Reservlyv1be.dtos.requestDtos.ChangePasswordDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.SignupRequestDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.UpdateUserRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.ApiResponse;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.UserResponseDto;
import com.mustycodified.Reservlyv1be.exceptions.ValidationException;
import com.mustycodified.Reservlyv1be.services.UserService;
import com.mustycodified.Reservlyv1be.utils.ResponseManager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class  UserController {
    private final ResponseManager responseManager;
    private final UserService userService;
    @Operation(summary = "Create a new user account",
            description = "After creating your account, a verification code will be sent to your provided email. \n" +
                    "Copy the code from you email and enter it in the 'activate-account end point'.\n" +
                    "If no code was sent to you, then use the 'resend-token' end point to resend a new activation code")
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid SignupRequestDto signupRequest) {
        return new ResponseEntity<>(userService.signUp(signupRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> getUser(@PathVariable String userId) {
        UserResponseDto userResponseDto = userService.getUser(userId);
        return responseManager.success(userResponseDto);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<Object>> getUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<UserResponseDto> userResponseDtos = userService.getUsers(page, limit, sortBy, sortDir);
        return responseManager.success(userResponseDtos);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity <HttpStatus> deleteUser( @PathVariable String id){
        userService.deleteUser(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT)  ;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> updateUser(@PathVariable String userId,
                                                          @RequestBody @Valid UpdateUserRequestDto request) {
        UserResponseDto userResponseDto = userService.updateUser(userId, request);
        return responseManager.success(userResponseDto);

    }
    @Operation(summary = "Updates a logged in user password, generates a new Bearer token and blacklists the old token")
    @PostMapping("/update-password")
    public ResponseEntity<ApiResponse<UserResponseDto>> updatePassword(@RequestBody ChangePasswordDto changePasswordDto, @RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer")) {
            token = token.replace("Bearer", "").replace("\\s", "");
        } else throw new ValidationException( "Invalid access token. Pls ensure token starts with 'Bearer '");
        changePasswordDto.setToken(token);

        return ResponseEntity.ok(new ApiResponse<>("Password updated",true, userService.updatePassword(changePasswordDto)));
    }
}
