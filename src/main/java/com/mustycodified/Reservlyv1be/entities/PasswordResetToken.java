package com.mustycodified.Reservlyv1be.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "token_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PasswordResetToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private long id;

    @Column(length = 500)
    private String token;

    @OneToOne
    @JoinColumn(name = "user_tbl_id")
    private User user;
}
