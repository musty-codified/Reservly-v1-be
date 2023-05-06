package com.mustycodified.Reservlyv1be.services;

import com.mustycodified.Reservlyv1be.dtos.requests.JavaMailDto;
import org.springframework.http.ResponseEntity;

public interface JavaMailService {
    ResponseEntity<String> sendMail(JavaMailDto mailDto);

}
