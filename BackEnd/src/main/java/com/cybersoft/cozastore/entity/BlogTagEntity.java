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
    @MapsId("idBlog")
    @JoinColumn(name = "idBlog",insertable = true,updatable = true, nullable = false)
    private BlogEntity blog;

    @ManyToOne
    @MapsId("idTag")
    @JoinColumn(name = "idTag",insertable = true,updatable = true, nullable = false)
    private TagEntity tag;

}
