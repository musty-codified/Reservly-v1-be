package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.ReservlyV1BeApplication;
import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
import com.mustycodified.Reservlyv1be.dtos.requests.JavaMailDto;
import com.mustycodified.Reservlyv1be.dtos.requests.ForgotPasswordRequest;
import com.mustycodified.Reservlyv1be.dtos.requests.PasswordResetRequest;
import com.mustycodified.Reservlyv1be.entities.PasswordResetToken;
import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.enums.ResponseCodeEnum;
import com.mustycodified.Reservlyv1be.respositories.PasswordResetRepository;
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
    private final PasswordResetRepository passwordResetRepository;
    private final AppUtils appUtil;
    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailService mailService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservlyV1BeApplication.class);
    JavaMailDto mailDto;
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

         mailDto = new JavaMailDto();
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
//user.setRole(getUserRoles(Collections.singleton(String.valueOf(Role.CUSTOMER))));
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

    @Override
    public BaseResponse forgotPasswordRequest(ForgotPasswordRequest forgotPasswordRequest) {

        String email = forgotPasswordRequest.getEmail();
       Optional <User> user = userRepository.findByEmail(email);
        LOGGER.info("User found with email: " + email);

        LOGGER.info("User found " + user.isPresent());
       if(user.isPresent()) {

        String token = jwtUtil.generatePasswordResetToken(email);
        LOGGER.info("password reset token is " + token);
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user.get());
        passwordResetRepository.save(resetToken);

//        String body =  "<h3> Hi " + new CreateUserRequest().getFirstName() +
//                " Please click the link below to set your new password <a href=" + forgotPasswordUrl + "><br>Activate</a></h3>";
        String forgotPasswordUrl = "http://localhost:8080/api/v1/users/reset-password?token= " + token ;
           String body = String.format("%s%s", forgotPasswordUrl, token + " expires in 15 minutes.");
        mailDto = new JavaMailDto();
        mailDto.setReceiverEmail(email);
        mailDto.setSubject("Follow the instructions below to reset your password");
        mailDto.setBody(body);
        mailService.sendMail(mailDto);

        return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.SUCCESS,
                "Kindly check your email to to reset your password");
    }  else {
       return responseCodeUtil.updateResponseData( response,ResponseCodeEnum.ERROR, "This User does not exist.");
       }
    }

    @Override
    public BaseResponse resetPassword(PasswordResetRequest passwordResetRequest) {

        boolean hasTokenExpired = JwtUtils.hasTokenExpired(passwordResetRequest.getToken());
        if (!hasTokenExpired) {
            Optional<PasswordResetToken> passwordResetToken = passwordResetRepository.findByToken(passwordResetRequest.getToken());
            if (passwordResetToken.isPresent()) {
                String encodedPassword = passwordEncoder.encode(passwordResetRequest.getPassword());

                PasswordResetToken resetToken = passwordResetToken.get();
                User user = resetToken.getUser();
                user.setPassword(encodedPassword);
             User savedUser = userRepository.save(user);

             if(savedUser != null && savedUser.getPassword().equalsIgnoreCase(encodedPassword)){
                 return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.SUCCESS,
                         "Password Reset Successful");
             }
             passwordResetRepository.delete(resetToken);
            }
        }
        return responseCodeUtil.updateResponseData(response, ResponseCodeEnum.ERROR,
                "Password Reset unsuccessful");
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

