package com.mustycodified.Reservlyv1be.repositories;


import com.mustycodified.Reservlyv1be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
