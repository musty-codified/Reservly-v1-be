package com.mustycodified.Reservlyv1be.services;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.LoginResponseDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.ActivateUserDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.ChangePasswordDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.LoginRequestDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.SignupRequestDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.UpdateUserRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.UserResponseDto;
import org.springframework.data.domain.Page;

public interface UserService {
    UserResponseDto signUp(SignupRequestDto signupRequest);
    UserResponseDto getUser(String userId);
    Page<UserResponseDto> getUsers(int page, int limit, String sortBy, String sortDir);
     void deleteUser(String userId);
    UserResponseDto updateUser(String userId, UpdateUserRequestDto updateRequestDto);
    String sendToken(String userEmail, String mailSubject);

    UserResponseDto activateUser(ActivateUserDto activateUserDto);

    String resetPassword(ChangePasswordDto changePasswordDto);

    LoginResponseDto login(LoginRequestDto request);

    String logout(String token);

    UserResponseDto updatePassword(ChangePasswordDto changePasswordDto);
}
