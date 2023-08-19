//package com.mustycodified.Reservlyv1be.entities;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "address_tbl")
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//public class Address extends BaseEntity{
//
//    @Column(nullable = false, length = 30)
//    private String addressId;
//
//    @Column(nullable = false, length = 15)
//    private String city;
//
//    @Column(nullable = false, length = 15)
//    private String country;
//
//    @Column(nullable = false, length = 100)
//    private String streetName;
//
//    @Column(nullable = false, length = 7)
//    private String postalCode;
//
//    @Column(nullable = false, length = 10)
//    private String type;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User guestDetails;
//}
