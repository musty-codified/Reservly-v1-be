package com.mustycodified.Reservlyv1be.security;

import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        String password = user.getPassword() == null || user.getPassword().isEmpty() ? "****" : user.getPassword();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), password, Arrays.asList());
    }

}