package com.mustycodified.Reservlyv1be.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private String roomType;

    private String description;

    @Column(nullable = false)
    private boolean isAvailable;

    @OneToMany(mappedBy = "room")
    private Set<Booking> bookings;
}
