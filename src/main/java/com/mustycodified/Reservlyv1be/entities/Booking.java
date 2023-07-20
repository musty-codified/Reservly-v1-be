package com.mustycodified.Reservlyv1be.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mustycodified.Reservlyv1be.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Booking extends BaseEntity{

    private Date checkInDate;

    private Date checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn( name = "guest_id")
    private Guest guest;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn( name = "room_id")
    private Room room;
}
