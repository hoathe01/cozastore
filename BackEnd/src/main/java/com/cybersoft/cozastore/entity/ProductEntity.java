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
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "quanity")
    private int quanity;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "idSize")
    private SizeEntity sizeEntity;

    @ManyToOne
    @JoinColumn(name = "idColor")
    private ColorEntity colorEntity;

    @Column(name = "createDate")
    private Date createDate;

    @OneToMany(mappedBy = "productEntity")
    private List<CartEntity> listCart;

    @OneToMany(mappedBy = "product")
    private List<ProductOrderEntity> listOrder;

}
