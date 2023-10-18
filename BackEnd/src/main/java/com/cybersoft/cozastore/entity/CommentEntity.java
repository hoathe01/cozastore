 package com.cybersoft.cozastore.entity;
 import lombok.Data;
 import javax.persistence.*;
 import java.util.Date;


@Data
@Entity(name = "comment" )
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "idBlog")
  private BlogEntity blogEntity;
  
  @Column(name = "createDate")
  private Date createDate;

}
