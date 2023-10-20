package com.cybersoft.cozastore.payload.response;

import com.cybersoft.cozastore.entity.CartEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {
    private int id;
    private String name;
    private String image;
    private double price;
    private String description;
    private int quanity;
    private Date createDate;

    private List<CartResponse> listCart;

}
