package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;

public interface UserService {
    BaseResponse signUp(CreateUserRequest createUserRequest);

}
