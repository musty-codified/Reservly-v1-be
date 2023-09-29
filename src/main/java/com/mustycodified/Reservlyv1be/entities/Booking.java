package com.mustycodified.Reservlyv1be.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Column(name = "checkedInDate")
    private LocalDate checkInDate;

    @Column(name = "checkedOutDate")
    private LocalDate checkOutDate;

    private BigDecimal totalPrice;

    private String transactionStatus;

}
