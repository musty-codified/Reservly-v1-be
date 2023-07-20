package com.mustycodified.Reservlyv1be.repositories;

import com.mustycodified.Reservlyv1be.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
