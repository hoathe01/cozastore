package com.cybersoft.cozastore.entity;

import com.cybersoft.cozastore.entity.key.BlogTagKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
