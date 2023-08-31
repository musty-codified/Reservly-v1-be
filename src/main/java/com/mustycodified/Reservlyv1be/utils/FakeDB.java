package com.mustycodified.Reservlyv1be.utils;


import com.github.javafaker.Faker;
import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.entities.Wallet;
import com.mustycodified.Reservlyv1be.enums.Status;
import com.mustycodified.Reservlyv1be.enums.UserRoles;
import com.mustycodified.Reservlyv1be.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class FakeDB implements CommandLineRunner {
    private final Faker faker;
    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
             // Create and save Users with associated Wallets
            IntStream.rangeClosed(1, 10).forEach(i -> {
                User user = createUserWithWallet();
                userRepository.save(user);
            });
        }
       private User createUserWithWallet() {
            Wallet wallet = createRandomWallet();
            User user = User.builder()
                    .userId(faker.internet().uuid())
                    .email(faker.internet().emailAddress())
                    .password(faker.internet().password())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .status(Status.ACTIVE.name())
                    .phoneNumber(faker.phoneNumber().cellPhone())
                    .roles(UserRoles.GUEST.getAuthorities().stream().map(Objects::toString)
                            .collect(Collectors.joining(",")))
                    .country(faker.address().country())
                    .address(faker.address().fullAddress())
                    .lastLoginDate(faker.date().past(1000 * 60 * 60 * 10, TimeUnit.MILLISECONDS))
                    .wallet(wallet)  // Associate the wallet with the user
                    .build();
            wallet.setUserDetails(user); // Associate the user with the wallet
            return user;
        }
        private Wallet createRandomWallet() {
            return Wallet.builder()
                    .walletUUID(faker.internet().uuid())
                    .balance(BigDecimal.ZERO)  // Initialize with a balance
                    .build();
        }

    }

