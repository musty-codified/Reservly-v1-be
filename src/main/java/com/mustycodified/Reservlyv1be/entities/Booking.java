package com.mustycodified.Reservlyv1be.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Booking extends BaseEntity{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usr_id")
    private User user;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkedInDate")
    private Date checkInDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkedOutDate")
    private Date checkOutDate;

    private double totalPrice;

    private String transactionStatus;

}
