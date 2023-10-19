package com.cybersoft.cozastore.entity.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class BlogTagKey implements Serializable {
    private int idBlog;
    private int idTag;
}
