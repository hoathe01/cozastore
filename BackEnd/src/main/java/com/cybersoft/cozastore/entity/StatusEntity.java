package com.cybersoft.cozastore.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;


@Data
@Entity(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "createDate")
    private Date createDate;
    @OneToMany(mappedBy = "statusEntity")
    private List<OrdersEntity> ordersList;

}
