package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CartEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.payload.response.CartResponse;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.service.imp.ProductServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductResponse> getListProduct() {
        try {
            List<ProductEntity> productEntityList = productRepository.findAll();
            return productEntityList.stream().map(productEntity -> ProductResponse.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .image(productEntity.getImage())
                    .price(productEntity.getPrice())
                    .description(productEntity.getDescription())
                    .quanity(productEntity.getQuanity())
                    .createDate(productEntity.getCreateDate())
                    .listCart(null)

                    .build()).toList();

        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return null;
        }
//        productEntity.getListCart().stream().map(cartEntity -> CartResponse.builder()
//
//                .createDate(cartEntity.getCreateDate())
//                .build()).toList())
    }
}
