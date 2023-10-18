 package com.cybersoft.cozastore.entity;
 import lombok.Data;

 import javax.persistence.*;
 import javax.persistence.Entity;
 import java.util.Date;


@Data
@Entity(name = "carousel" )
public class CarouselEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(name = "title")
  private String title;
  
  @Column(name = "image")
  private String image;
  
  @Column(name = "content")
  private String content;

  @Column(name = "createDate")
  private Date createDate;

  @ManyToOne
  @JoinColumn(name = "idCategory")
  private CategoryEntity categoryEntity;

}
