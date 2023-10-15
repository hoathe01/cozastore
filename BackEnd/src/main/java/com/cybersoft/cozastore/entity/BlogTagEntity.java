 package com.none.entity;
 import jakarta.persistence.*;
 import lombok.Data;
 import java.util.Date;


@Data
@Entity(name = "blogtag" )
public class BlogTagEntity {

  
  @Column(name = "idBlog")
  private int idBlog;
  
  @Column(name = "idTag")
  private int idTag;
  
  @Column(name = "createDate")
  private Date createDate;

}
