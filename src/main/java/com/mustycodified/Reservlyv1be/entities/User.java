package com.mustycodified.Reservlyv1be.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long id;

    @NotNull(message = "First name cannot be missing or empty")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotNull(message = "Last name cannot be missing or empty")
    @Column(nullable = false, length = 50)
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    private String address;

    private Boolean isEmailVerified = false;

    private String emailVerificationToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Booking> bookings;
      @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
      @JoinTable( name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id"),
              inverseJoinColumns = @JoinColumn(name = "role_id")
      )
    private Collection<Role> roles;

}
