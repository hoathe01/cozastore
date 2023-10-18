 package com.cybersoft.cozastore.entity;

 import lombok.Data;
 import org.apache.catalina.User;

 import javax.persistence.*;
 import java.util.Date;


@Data
@Entity(name = "cart" )
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
