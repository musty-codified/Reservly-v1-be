package com.mustycodified.Reservlyv1be.respositories;

import com.mustycodified.Reservlyv1be.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {

   Optional<PasswordResetToken> findByToken(String token);
}
