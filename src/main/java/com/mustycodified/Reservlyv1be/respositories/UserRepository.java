package com.mustycodified.Reservlyv1be.respositories;


import com.mustycodified.Reservlyv1be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
   Optional<User> findByEmail(String email);
   Optional<User> findByEmailVerificationToken(String token);
}
