package com.mustycodified.Reservlyv1be.services;
import com.mustycodified.Reservlyv1be.dtos.ResponseDtos.LoginResponseDto;
import com.mustycodified.Reservlyv1be.dtos.RequestDtos.ActivateUserDto;
import com.mustycodified.Reservlyv1be.dtos.RequestDtos.ChangePasswordDto;
import com.mustycodified.Reservlyv1be.dtos.RequestDtos.LoginRequestDto;
import com.mustycodified.Reservlyv1be.dtos.RequestDtos.SignupRequestDto;
import com.mustycodified.Reservlyv1be.dtos.RequestDtos.UpdateUserRequestDto;
import com.mustycodified.Reservlyv1be.dtos.ResponseDtos.UserResponseDto;
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

}
