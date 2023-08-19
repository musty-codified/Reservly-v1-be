//package com.mustycodified.Reservlyv1be.entities;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "room_type_tbl")
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@Builder
//public class RoomType extends BaseEntity{
//
//    private String name;
//
//    @Column(length=1000)
//    private String description;
//
//    @OneToMany(mappedBy = "roomType", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private Set<Room> rooms;
//}
