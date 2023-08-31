package com.mustycodified.Reservlyv1be.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender getJavaMailSender(){

//        Dotenv dotenv = Dotenv.load();
//        String email = dotenv.get("EMAIL_ADDRESS"); //you can add your email here if you did not set up .env file
//        String password = dotenv.get("PASSWORD");   //you can add your password here if you did not set up .env file

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("reservlylodging@gmail.com");
        mailSender.setPassword("jgobityfdqurychz");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
}
