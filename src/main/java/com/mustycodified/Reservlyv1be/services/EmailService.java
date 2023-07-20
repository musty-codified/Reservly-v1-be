package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.EmailDto;
import org.springframework.http.ResponseEntity;

public interface EmailService {

    ResponseEntity<String> sendMail(EmailDto mailDto);

}
