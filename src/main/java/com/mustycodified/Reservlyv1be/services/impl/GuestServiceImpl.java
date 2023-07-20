package com.mustycodified.Reservlyv1be.services.impl;


import com.mustycodified.Reservlyv1be.dtos.EmailDto;
import com.mustycodified.Reservlyv1be.dtos.GuestSignupRequestDto;
import com.mustycodified.Reservlyv1be.dtos.SignupResponseDto;
import com.mustycodified.Reservlyv1be.entities.Guest;
import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.enums.Gender;
import com.mustycodified.Reservlyv1be.enums.UserRole;
import com.mustycodified.Reservlyv1be.repositories.GuestRepository;
import com.mustycodified.Reservlyv1be.repositories.UserRepository;
import com.mustycodified.Reservlyv1be.services.EmailService;
import com.mustycodified.Reservlyv1be.services.GuestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final UserRepository userRepository;


    private final ModelMapper modelMapper;

    private final EmailService emailService;

    @Override
    public SignupResponseDto createGuest(GuestSignupRequestDto signupRequestDto) {
//             SignupResponseDto responseDto = new SignupResponseDto();
        Guest guestEntity = new Guest();
        User userEntity = new User();

        modelMapper.map(signupRequestDto, userEntity);
        userEntity.setGuest(guestEntity);
        userEntity.setRole(UserRole.GUEST);
        userEntity.setGender(Gender.valueOf(signupRequestDto.getGender().toUpperCase()));
        userEntity.setEmailVerificationStatus(false);
        userEntity.setPassword(signupRequestDto.getPassword());
        guestEntity.setUser(userEntity);

         guestRepository.save(guestEntity);
         userRepository.save(userEntity);


        EmailDto mailDto;
        mailDto = new EmailDto();
        mailDto.setTo(signupRequestDto.getEmail());
        mailDto.setBody("Please verify your email");
        mailDto.setSubject("Email verification");
        emailService.sendMail(mailDto);

        SignupResponseDto returnValue=  modelMapper.map(userEntity, SignupResponseDto.class);
        return returnValue;

    }

    echo "# Reservly-v1-be" >> README.md
    git init
    git add README.md
    git commit -m "first commit"
    git branch -M main
    git remote add origin https://github.com/musty-codified/Reservly-v1-be.git
    git push -u origin main
}
