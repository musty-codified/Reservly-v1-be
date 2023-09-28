package com.mustycodified.Reservlyv1be.repositories;

import com.mustycodified.Reservlyv1be.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByRoomNumber(String roomNumber);
}
