package com.cybersoft.cozastore.payload.response;

import com.cybersoft.cozastore.entity.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {

    private int id;
    private String name;
    private List<String> image;
    private double price;
    private String description;
    private int quanity;
    private CategoryResponse categoryEntity;
    private SizeResponse sizeEntity;
    private ColorResponse colorEntity;
    private Date createDate;

    private List<CartResponse> listCart;
//    private List<ProductOrderResponse> listOrder;
}
