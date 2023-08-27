package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.dtos.responseDtos.LoginResponseDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.ActivateUserDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.ChangePasswordDto;
import com.mustycodified.Reservlyv1be.dtos.EmailDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.LoginRequestDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.SignupRequestDto;
import com.mustycodified.Reservlyv1be.dtos.requestDtos.UpdateUserRequestDto;
import com.mustycodified.Reservlyv1be.dtos.responseDtos.UserResponseDto;
import com.mustycodified.Reservlyv1be.entities.User;
//import com.mustycodified.Reservlyv1be.entities.Wallet;
import com.mustycodified.Reservlyv1be.entities.Wallet;
import com.mustycodified.Reservlyv1be.enums.Status;
import com.mustycodified.Reservlyv1be.enums.UserRoles;
import com.mustycodified.Reservlyv1be.exceptions.RecordAlreadyExistException;
import com.mustycodified.Reservlyv1be.exceptions.UserNotFoundException;
import com.mustycodified.Reservlyv1be.exceptions.ValidationException;
import com.mustycodified.Reservlyv1be.repositories.*;
import com.mustycodified.Reservlyv1be.security.CustomUserDetailService;
import com.mustycodified.Reservlyv1be.security.JwtUtils;
import com.mustycodified.Reservlyv1be.services.EmailService;
import com.mustycodified.Reservlyv1be.services.UserService;
import com.mustycodified.Reservlyv1be.utils.AppUtils;
import com.mustycodified.Reservlyv1be.utils.Mapper;
import com.mustycodified.Reservlyv1be.utils.LocalMemStorage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AppUtils appUtil;
    private final JwtUtils jwtUtil;
    private final CustomUserDetailService customUserDetailService;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final LocalMemStorage memStorage;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;


    @Override
    public UserResponseDto signUp(SignupRequestDto signupRequest) {

        if (!appUtil.validEmail(signupRequest.getEmail())) {
            throw new ValidationException("Invalid email address {"+ signupRequest.getEmail()+"}");
        }
        boolean emailExist = userRepository.existsByEmail(signupRequest.getEmail());

        if (emailExist)
            throw new RecordAlreadyExistException("Record already exist");

        User user = User.builder()
                .userId(appUtil.generateSerialNumber("usr"))
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .username(signupRequest.getUsername())
                .status(Status.INACTIVE.name())
                .roles(UserRoles.GUEST.getAuthorities().stream()
                        .map(Object::toString).collect(Collectors.joining(",")))
                .address(signupRequest.getAddress())
                .phoneNumber(signupRequest.getPhoneNumber())
                .build();
                User storedUser = userRepository.save(user);

        sendToken(storedUser.getEmail(), "One last step to complete your registration: Activate your account");
         return appUtil.getMapper().convertValue(storedUser, UserResponseDto.class);
    }


    @Override
    public String sendToken(String userEmail, String subject) {
        if (!userRepository.existsByEmail(userEmail))
            throw new ValidationException("User with email: " + userEmail + " does not exists");

        String token = appUtil.generateSerialNumber("verify");
        memStorage.save(userEmail, token, 900); //expires in 15mins

        EmailDto mailDto = EmailDto.builder()
                .to(userEmail)
                .subject(subject.toUpperCase())
                .body(String.format("Use this generated token to %s: %s (Expires in 15mins)", subject.toLowerCase(), token))
                .build();

        emailService.sendMail(mailDto);

        return "Verification Token sent";
    }

    @Override
    public UserResponseDto activateUser(ActivateUserDto activateUserDto) {

        validateToken(activateUserDto.getEmail(), activateUserDto.getEmailVerificationToken());

        User userToActivate = userRepository.findByEmail(activateUserDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userToActivate.setStatus(Status.ACTIVE.name());
        UserResponseDto userResponseDto = appUtil.getMapper().convertValue(userRepository.save(userToActivate), UserResponseDto.class);

        Wallet newWallet = Wallet.builder()
                .walletUUID(appUtil.generateSerialNumber("w"))
                .balance(BigDecimal.ZERO)
                .userDetails(userToActivate)
                .build();

              walletRepository.save(newWallet);

        EmailDto mailDto = EmailDto.builder()
                .to(userToActivate.getEmail())
                .subject("YOUR ACCOUNT IS ACTIVATED")
                .body(String.format("Hi %s, \n You have successfully activated your account. Kindly login to start making use of the app", userResponseDto.getUsername()))
                .build();

        emailService.sendMail(mailDto);

        return userResponseDto;
    }

    @Override
    public String resetPassword(ChangePasswordDto changePasswordDto) {
        validateToken(changePasswordDto.getEmail(), changePasswordDto.getToken());

        User userToResetPassword = userRepository.findByEmail(changePasswordDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userToResetPassword.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
        userRepository.save(userToResetPassword);

        EmailDto mailDto = EmailDto.builder()
                .to(userToResetPassword.getEmail())
                .subject("PASSWORD RESET SUCCESSFUL")
                .body(String.format("Hi %s, \n You have successfully reset your password. Kindly login with your new password. " +
                        "\n If you did not authorize this, kindly create a ticket from our complaint section on the app", userToResetPassword.getUsername()))
                .build();

        emailService.sendMail(mailDto);

        return "password reset successful";    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()
                -> new BadCredentialsException("Bad credentials"));
        logger.info("Email is: " + request.getEmail());

        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new BadCredentialsException("Bad credentials");
        }

        if (authentication.isAuthenticated()){
            if (user.getStatus().equals(Status.INACTIVE.name())){
                throw new ValidationException("User not active");
            }
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getEmail());

        String token = jwtUtil.generateToken(userDetails);
        user.setLastLoginDate(new Date());

        LoginResponseDto responseDto = LoginResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .token(token)
                .build();
        return responseDto;    }

    @Override
    public String logout(String headerToken) {
        if (headerToken.startsWith("Bearer")) {
            headerToken = headerToken.replace("Bearer", "").replace("\\s", "");
        }
        blacklistToken(headerToken);
        return "Logout successful";    }

    @Override
    public UserResponseDto updatePassword(ChangePasswordDto changePasswordDto) {
        if (jwtUtil.isTokenExpired(changePasswordDto.getToken()))
            throw new ValidationException("Request token has expired");

        blacklistToken(changePasswordDto.getToken());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String newToken = jwtUtil.generateToken(userDetails);

        UserResponseDto userResponseDto = appUtil.getMapper().convertValue(userRepository.findByEmail(changePasswordDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User does not exist")), UserResponseDto.class);
        userResponseDto.setToken(newToken);

        return userResponseDto;
    }


    private void blacklistToken(String headerToken) {
        Date expiryDate = jwtUtil.extractExpiration(headerToken);
        int expiryTimeInSeconds = (int) ((expiryDate.getTime() - new Date().getTime())/1000);
        memStorage.setBlacklist(headerToken, expiryTimeInSeconds);
    }

    @Override
    public UserResponseDto getUser(String userId) {
        User user =  userRepository.findByUserId(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found with ID " + userId));
        return Mapper.toUserDto(user);
    }

    @Override
    public Page<UserResponseDto> getUsers(int page, int limit, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageableRequest = PageRequest.of(page, limit, sort);
        Page<User> userPage = userRepository.findAll(pageableRequest);

        List<UserResponseDto> userResponseDtos = userPage.stream()
                .map(Mapper::toUserDto).collect(Collectors.toList());
        if (page > 0) page = page -1;
        int max = Math.min(limit * (page + 1), userResponseDtos.size());
        int min = page * limit;
        return new PageImpl<>(userResponseDtos.subList(min, max), pageableRequest, userResponseDtos.size());
    }

    @Override
    public void deleteUser(String userId) {
       User user = userRepository.findByUserId(userId)
               .orElseThrow(()-> new UserNotFoundException("User not found"));
       userRepository.delete(user);
    }

    @Override
    public UserResponseDto updateUser(String userId, UpdateUserRequestDto updateRequestDto) {
        User user = userRepository.findByUserId(userId).
                orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setEmail(updateRequestDto.getEmail());
        user.setUsername(updateRequestDto.getUsername());
        user.setUserId(appUtil.generateUserId(10));User updatedUser = userRepository.save(user);

        return Mapper.toUserDto(updatedUser);

    }


    public void validateToken(String memCachedKey, String token) {

        if (!appUtil.validEmail(memCachedKey))
            throw new ValidationException("Invalid email");

        String cachedToken = memStorage.getValueByKey(memCachedKey);
        if (cachedToken == null)
            throw new ValidationException("Token expired");
        if (!cachedToken.equals(token))
            throw new ValidationException("Invalid token");

        if (!userRepository.existsByEmail(memCachedKey))
            throw new UserNotFoundException("User not found");
    }

}