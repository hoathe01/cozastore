package com.cybersoft.cozastore.entity;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Data
@Setter@Entity(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity productEntity;

    @Column(name = "quanity")
    private int quanity;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity userEntity;

    @Column(name = "createDate")
    private Date createDate;

}
