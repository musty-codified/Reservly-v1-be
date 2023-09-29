package com.mustycodified.Reservlyv1be.entities;


import com.mustycodified.Reservlyv1be.enums.RoomType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "room_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Room extends BaseEntity{

    @Column(nullable=false, unique = true)
    private String roomNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private String description;

    @Column(nullable = false)
    private BigDecimal pricePerNight;

    @Column(nullable = false)
    private boolean isAvailable;

    @OneToMany(mappedBy = "room")
    private Set<Booking> bookings;
}
