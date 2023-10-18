 package com.cybersoft.cozastore.entity;

 import lombok.Data;

 import javax.persistence.*;
 import java.util.Date;
 import java.util.List;


 @Data
@Entity(name = "color" )
public class ColorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "createDate")
  private Date createDate;

  @OneToMany(mappedBy = "colorEntity")
  private List<ProductEntity> productList;

}
