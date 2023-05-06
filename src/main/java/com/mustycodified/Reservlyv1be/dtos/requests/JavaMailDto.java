package com.mustycodified.Reservlyv1be.dtos.requests;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JavaMailDto {
    private String receiverEmail;
    private String subject;
    private String body;
}
