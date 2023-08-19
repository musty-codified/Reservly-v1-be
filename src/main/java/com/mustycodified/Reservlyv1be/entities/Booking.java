//package com.mustycodified.Reservlyv1be.entities;
//
//
//import com.mustycodified.Reservlyv1be.enums.BookingStatus;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "booking_tbl")
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@Builder
//public class Booking extends BaseEntity{
//
//    private String checkInDate;
//
//    private String checkOutDate;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private BookingStatus bookingStatus;
//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
//            CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn( name = "user_id")
//    private User userDetails;
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "room_id")
//    private Room roomDetails;
//}
