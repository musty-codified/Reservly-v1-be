package com.mustycodified.Reservlyv1be.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roomType_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private long id;

    private String name;
    private String description;

    @OneToOne(mappedBy = "roomType", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Room room;
}
