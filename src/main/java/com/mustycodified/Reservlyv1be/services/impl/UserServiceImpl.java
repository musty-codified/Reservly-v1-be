package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.ReservlyV1BeApplication;
import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.dtos.requests.JavaMailDto;
import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.enums.ResponseCodeEnum;
import com.mustycodified.Reservlyv1be.respositories.UserRepository;
import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;
import com.mustycodified.Reservlyv1be.security.JwtUtils;
import com.mustycodified.Reservlyv1be.services.JavaMailService;
import com.mustycodified.Reservlyv1be.services.UserService;
import com.mustycodified.Reservlyv1be.utils.AppUtils;
import com.mustycodified.Reservlyv1be.utils.ResponseCodeUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    BaseResponse response = new BaseResponse();
    private final ResponseCodeUtil responseCodeUtil;
    private final UserRepository userRepository;
    private final AppUtils appUtil;
    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailService mailService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservlyV1BeApplication.class);
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

        if (userRepository.existsByEmail(createUserRequest.getEmail()))
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR,
                    "User already exist.");

        User newUser = new User();
        newUser.setFirstName(createUserRequest.getFirstName());
        newUser.setLastName(createUserRequest.getLastName());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPhoneNumber(appUtil.getFormattedNumber(createUserRequest.getPhoneNumber()));
        newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        String token = jwtUtil.generateEmailVerificationToken(createUserRequest.getEmail());
        newUser.setEmailVerificationToken(token);

        newUser.setIsEmailVerified(false);
        userRepository.save(newUser);

        String URL = "http://localhost:8080/api/v1/user/email-verification?token= " + token;

        String subject = "One last step to complete your registration with Reservly";
        String body =  "<h3> Hi " + createUserRequest.getFirstName() + " Please click the link below to verify your email <a href=" + URL + "><br>Activate</a></h3>";

        JavaMailDto mailDto = new JavaMailDto();
        mailDto.setReceiverEmail(createUserRequest.getEmail());
        mailDto.setBody(body);
        mailDto.setSubject(subject);
        mailService.sendMail(mailDto);

        return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.SUCCESS,
                "You have successful registered. Check your email to verify your account");
    }

    @Override
    public BaseResponse verifyEmailToken(String token) {
        BaseResponse response = new BaseResponse();
        LOGGER.info("token received: " + token);
        Optional<User> existingUser = userRepository.findByEmailVerificationToken(token);
        LOGGER.info("User found: " + existingUser.isPresent());

        if (existingUser.isPresent()) {
            boolean hasTokenExpired = JwtUtils.hasTokenExpired(token);
            if(!hasTokenExpired){
                User user = existingUser.get();
//            user.setRole(getUserRoles(Collections.singleton(String.valueOf(Role.CUSTOMER))));
                LOGGER.info("User before update: " + user);
                LOGGER.info("Email verification token before update: " + user.getEmailVerificationToken());

                user.setEmailVerificationToken(null);
                user.setIsEmailVerified(true);

                LOGGER.info("Email verification token after update: " + user.getEmailVerificationToken());

                userRepository.save(user);
                LOGGER.info("User after update: " + user);
            }

            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.SUCCESS,
                    "Account verification successful");
        } else {
            return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR,
                    "User not found");
        }
    }

    @Override  //http://localhost:8080/users/authenticate
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->new UsernameNotFoundException("User not found with email: " + email));
        String password = user.getPassword() == null || user.getPassword().isEmpty() ? "****" : user.getPassword();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), password, user.getIsEmailVerified(),
                true,
                true,
                true,
                new ArrayList<>());
    }
}

