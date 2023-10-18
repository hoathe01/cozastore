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
@Entity(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "createDate")
    private Date createDate;

    @OneToMany(mappedBy = "role")
    List<UserEntity> userList;

}
