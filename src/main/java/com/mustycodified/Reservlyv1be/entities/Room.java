package com.mustycodified.Reservlyv1be.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "room_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Room extends BaseEntity {

    private int roomNum;

    private double price;

    private int roomCapacity;

    @ElementCollection
    @CollectionTable(
            name = "room_amenities",
            joinColumns = @JoinColumn(name = "room_id")
    )
    @Column(name = "amenity")
    private List<String> amenities;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "roomType_id")
    private RoomType roomType;

    private boolean isRoomAvailable = true;

}
