package com.mustycodified.Reservlyv1be.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "room_type_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomType extends BaseEntity{
    private String name;

    @Column(length=1000)
    private String description;

    @OneToOne(mappedBy = "roomType", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Room room;
}
