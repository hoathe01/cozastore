 package com.cybersoft.cozastore.entity;
 import lombok.Data;

 import javax.persistence.*;
 import javax.persistence.Entity;
 import java.util.Date;
 import java.util.List;


 @Data
@Entity(name = "blog")
public class BlogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(name = "title")
  private String title;
  
  @Column(name = "image")
  private String image;
  
  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "idUser")
  private UserEntity userEntity;

  @OneToMany(mappedBy = "blogEntity")
  private List<CommentEntity> listComment;
  
  @Column(name = "createDate")
  private Date createDate;

}
