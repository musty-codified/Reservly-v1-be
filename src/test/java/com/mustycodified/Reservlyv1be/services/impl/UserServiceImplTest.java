//package com.mustycodified.Reservlyv1be.services.impl;//package com.mustycodified.Reservlyv1be.services.impl;
//
//import com.mustycodified.Reservlyv1be.ReservlyV1BeApplication;
//import com.mustycodified.Reservlyv1be.dtos.requests.CreateUserRequest;
//import com.mustycodified.Reservlyv1be.entities.User;
//import com.mustycodified.Reservlyv1be.enums.ResponseCodeEnum;
//import com.mustycodified.Reservlyv1be.respositories.UserRepository;
//import com.mustycodified.Reservlyv1be.restartifacts.BaseResponse;
//import com.mustycodified.Reservlyv1be.security.JwtUtils;
//import com.mustycodified.Reservlyv1be.services.JavaMailService;
//import com.mustycodified.Reservlyv1be.utils.AppUtils;
//import com.mustycodified.Reservlyv1be.utils.ResponseCodeUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//class UserServiceImplTest {
//    @InjectMocks
//    UserServiceImpl userServiceImpl;
//    @Mock
//    private ResponseCodeUtil responseCodeUtil;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private AppUtils appUtil;
//    @Mock
//    private JwtUtils jwtUtil;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private JavaMailService mailService;
//    private static final Logger LOGGER = LoggerFactory.getLogger(ReservlyV1BeApplication.class);
//
//    User newUser;
//    CreateUserRequest request;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        newUser = new User();
//       newUser.setEmail("test@test.com");
//       newUser.setFirstName("Taylor");
//       newUser.setLastName("Swift");
//       newUser.setPassword("hjj30jw37u4yd");
//       newUser.setEmailVerificationToken("h5k4r8hy47");
//       newUser.setPhoneNumber("12345678910");
//    }
//
//    @Test
//    void signUp() {
//
//        // Mocking the necessary dependencies
//        when(userRepository.existsByEmail(anyString())).thenReturn(false);
//        when(jwtUtil.generateEmailVerificationToken(anyString())).thenReturn("dummy&token");
//        when(appUtil.getFormattedNumber(anyString())).thenReturn("12345678910");
//        when(passwordEncoder.encode(anyString())).thenReturn("hjj30jw37u4yd");
//
//        // Set up the expected save operation
//        when(userRepository.save(any(User.class))).thenReturn(newUser);
//
//       //Create the request object
//        request = new CreateUserRequest();
//        request.setFirstName("Taylor");
//        request.setLastName("Swift");
//        request.setEmail("test@test.com");
//        request.setPhoneNumber("1234567890");
//        request.setPassword("taylor1989");
//
//        // Mock the responseCodeUtil.updateResponseData() method to populate the response object
//        BaseResponse expectedResponse = new BaseResponse(ResponseCodeEnum.SUCCESS,
//                "you have successful registered. Check your email to verify your account");
//        when(responseCodeUtil.updateResponseData(any(BaseResponse.class), any(ResponseCodeEnum.class), anyString()))
//                .thenReturn(expectedResponse);
//
//        // Perform the signUp operation
//        BaseResponse response = userServiceImpl.signUp(request);
//        LOGGER.info("Response: " + response);
//        LOGGER.info("Response: " + expectedResponse);
//
//        // Verify the expected interactions
//        assertEquals(expectedResponse.getCode(), response.getCode());
//        assertEquals(expectedResponse.getDescription(), response.getDescription());
//        assertEquals(expectedResponse, response);
//        assertEquals(newUser.getFirstName(), request.getFirstName());
//        assertEquals(newUser.getLastName(), request.getLastName());
//        assertEquals(newUser.getEmail(), request.getEmail());
//        assertEquals(newUser.getPhoneNumber(), appUtil.getFormattedNumber(request.getPhoneNumber()));
//        assertEquals(newUser.getPassword(), passwordEncoder.encode(request.getPassword()));
//    }
//
//}
//
