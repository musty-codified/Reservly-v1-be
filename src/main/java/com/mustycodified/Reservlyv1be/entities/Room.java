package com.mustycodified.Reservlyv1be.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;

    private double roomPrice;

    private int roomCapacity;

    private int roomNum;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "type_id")
    private RoomType roomType;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private Boolean isRoomAvailable = true;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Booking> bookings;

}
