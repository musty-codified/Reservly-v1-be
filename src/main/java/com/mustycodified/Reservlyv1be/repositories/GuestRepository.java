package com.mustycodified.Reservlyv1be.repositories;


import com.mustycodified.Reservlyv1be.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
