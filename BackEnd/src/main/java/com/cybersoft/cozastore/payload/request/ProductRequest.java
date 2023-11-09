package com.cybersoft.cozastore.payload.request;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductRequest {

    private String name;
    private String image;
    private double price;
    private String description;
    private Date createDate;
    private int quanity;
    private int category;
    private int color;
    private int size;

}
