 package com.cybersoft.cozastore.entity;

 import lombok.Data;

 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;



 import java.util.Date;


@Data
@Entity(name = "blogtag" )
public class BlogTagEntity {

    @Id
  @Column(name = "idBlog")
  private int idBlog;
  
  @Column(name = "idTag")
  private int idTag;
  
  @Column(name = "createDate")
  private Date createDate;

}
