package com.cybersoft.cozastore.entity;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "createDate")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "idRole")
    private RoleEntity role;
}
