package com.cybersoft.cozastore.entity;

import com.cybersoft.cozastore.entity.key.ProductOrderKey;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity(name = "productorder")
public class ProductOrderEntity {

    @EmbeddedId
    private ProductOrderKey productOrderKey;

    @Column(name = "quanity")
    private int quanity;

    @Column(name = "price")
    private double price;

    @Column(name = "createDate")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "idProduct",insertable = false,updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "idOrder",insertable = false,updatable = false)
    private OrdersEntity orders;

}
