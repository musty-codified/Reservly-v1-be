package com.mustycodified.Reservlyv1be.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authority_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private long id;
    private String name;

    @ManyToMany(mappedBy = "authorities",
            cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Authority(String name) {
        this.name = name;
    }

}
