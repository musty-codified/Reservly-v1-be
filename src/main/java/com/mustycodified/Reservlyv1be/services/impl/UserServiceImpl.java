package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.enums.ResponseCodeEnum;
import com.mustycodified.Reservlyv1be.respositories.UserRepository;
import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;
import com.mustycodified.Reservlyv1be.services.UserService;
import com.mustycodified.Reservlyv1be.utils.AppUtils;
import com.mustycodified.Reservlyv1be.utils.ResponseCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    BaseResponse response = new BaseResponse();
    private final ResponseCodeUtil responseCodeUtil;
    private final UserRepository userRepository;
    private final AppUtils appUtil;
    @Override
    public BaseResponse signUp(CreateUserRequest createUserRequest) {
        if(createUserRequest.getFirstName().trim().length()==0 ||
                createUserRequest.getLastName().trim().length()==0)
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR,
                    "First name cannot be empty.");

        if (createUserRequest.getPhoneNumber().trim().length() == 0)
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR,
                    "Last name cannot be empty.");

        if (createUserRequest.getPassword().trim().length() == 0)
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR,
                    "Password cannot be empty.");

        Boolean isUserExist = userRepository.existsByEmail(createUserRequest.getEmail());
        if (isUserExist)
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR,
                    "User already exist.");

        User newUser = new User();
        newUser.setFirstName(createUserRequest.getFirstName());
        newUser.setLastName(createUserRequest.getLastName());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPhoneNumber(appUtil.getFormattedNumber(createUserRequest.getPhoneNumber()));
        newUser.setPassword("swifty1989");
        userRepository.save(newUser);

        return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.SUCCESS,
                "You have successful registered. Check your email to verify your account");
    }


    }

