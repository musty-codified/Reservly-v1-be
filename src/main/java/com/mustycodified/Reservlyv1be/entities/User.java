package com.mustycodified.Reservlyv1be.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mustycodified.Reservlyv1be.enums.Gender;
import com.mustycodified.Reservlyv1be.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User extends BaseEntity{

    @NotNull(message = "First name cannot be missing or empty")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotNull(message = "Last name cannot be missing or empty")
    @Column(nullable = false, length = 50)
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String date_of_birth;

    private String phone;

    private String nationality;

    private Boolean emailVerificationStatus;

    private boolean emailVerificationToken = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Guest guest;

}
