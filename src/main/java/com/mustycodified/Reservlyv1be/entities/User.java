package com.mustycodified.Reservlyv1be.entities;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User extends BaseEntity{

    private static final long serialVersionUID = 2L;

    @Column(unique = true)
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @NotNull(message = "Firstname cannot be missing or empty")
    private String firstName;

    @NotNull(message = "Lastname cannot be missing or empty")
    private String lastName;

    @Column(nullable = false)
    private String status;

    @Column(length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private String roles;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    private Date lastLoginDate;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

}
