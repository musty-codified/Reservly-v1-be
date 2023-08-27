package com.mustycodified.Reservlyv1be.repositories;

import com.mustycodified.Reservlyv1be.entities.User;
import com.mustycodified.Reservlyv1be.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUserDetails(User user);
}
