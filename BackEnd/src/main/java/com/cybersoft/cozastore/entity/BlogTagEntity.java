package com.cybersoft.cozastore.entity;

import com.cybersoft.cozastore.entity.key.BlogTagKey;
import lombok.Data;

import javax.persistence.*;


import java.util.Date;


@Data
@Entity(name = "blogTag")
public class BlogTagEntity {
    @EmbeddedId
    private BlogTagKey blogTagKey;

    @Column(name = "createDate")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "idBlog",insertable = false,updatable = false)
    private BlogEntity blog;

    @ManyToOne
    @JoinColumn(name = "idTag",insertable = false,updatable = false)
    private TagEntity tag;

}
