package com.cybersoft.cozastore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private StatusEntity statusEntity;

    @Column(name = "createDate")
    private Date createDate;

    @OneToMany(mappedBy = "orders")
    private List<ProductOrderEntity> productList;

}
