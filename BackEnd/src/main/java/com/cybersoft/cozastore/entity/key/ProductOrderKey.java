package com.cybersoft.cozastore.entity.key;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProductOrderKey implements Serializable {

    private int idOrder;
    private int idProduct;


}
