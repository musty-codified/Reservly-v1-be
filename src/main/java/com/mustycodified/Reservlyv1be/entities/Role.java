//package com.mustycodified.Reservlyv1be.entities;
//
//import com.mustycodified.Reservlyv1be.enums.UserRoles;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Accessors(chain = true)
//@Entity
//@Table(name = "role")
//public class Role extends BaseEntity{
//
//    @ManyToMany(mappedBy = "roles")
//    private Collection<User> users;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private UserRoles role;
//
//}
