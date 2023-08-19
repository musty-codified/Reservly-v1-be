//package com.mustycodified.Reservlyv1be.entities;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Set;
//
//
//@Entity
//@Table(name = "room_tbl")
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@Builder
//public class Room extends BaseEntity {
//
//    private int roomNum;
//
//    private double price;
//
//    private int roomCapacity;
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "roomType_id")
//    private RoomType roomType;
//
//    private boolean isRoomAvailable = true;
//
//    @OneToMany( mappedBy = "roomDetails")
//    private Set<Booking> bookings;
//}
