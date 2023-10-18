 package com.cybersoft.cozastore.entity;

 import lombok.Data;

 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;
 import java.util.Date;


@Data
@Entity(name = "productorder" )
public class ProductOrderEntity {

    @Id
  @Column(name = "idProduct")
  private int idProduct;
  
  @Column(name = "idOrder")
  private int idOrder;
  
  @Column(name = "quanity")
  private int quanity;
  
  @Column(name = "price")
  private double price;
  
  @Column(name = "createDate")
  private Date createDate;

}
