package com.cybersoft.cozastore.entity;

import javax.persistence.*;

import lombok.*;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "userEntity")
    private List<BlogEntity> blogList;

    @OneToMany(mappedBy = "userEntity")
    private List<CartEntity> cartList;

}
