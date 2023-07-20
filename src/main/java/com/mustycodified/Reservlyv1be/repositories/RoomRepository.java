package com.mustycodified.Reservlyv1be.repositories;


import com.mustycodified.Reservlyv1be.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
