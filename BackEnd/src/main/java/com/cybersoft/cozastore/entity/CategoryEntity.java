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
@Entity(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "createDate")
    private Date createDate;

    @OneToMany(mappedBy = "categoryEntity")
    private List<CarouselEntity> carouselList;

    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> productList;
}
