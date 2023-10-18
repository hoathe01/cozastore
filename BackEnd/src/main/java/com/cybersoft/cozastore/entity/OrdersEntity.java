 package com.cybersoft.cozastore.entity;
 import lombok.Data;

 import javax.persistence.*;
 import javax.persistence.Entity;
 import java.util.Date;


@Data
@Entity(name = "orders" )
public class OrdersEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(name = "idUser")
  private int idUser;

  @ManyToOne
  @JoinColumn(name = "idStatus")
  private StatusEntity statusEntity;
  
  @Column(name = "createDate")
  private Date createDate;

}
