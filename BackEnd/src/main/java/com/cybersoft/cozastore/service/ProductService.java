package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.payload.response.*;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.service.imp.ProductServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductResponse> getListProduct() {
        try{
            List<ProductEntity> getListProduct = productRepository.findAll();
            return getListProduct.stream().map(productEntity -> ProductResponse.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .image(productEntity.getImage())
                    .price(productEntity.getPrice())
                    .description(productEntity.getDescription())
                    .quanity(productEntity.getQuanity())
                    .categoryEntity(CategoryResponse.builder()
                            .name(productEntity.getCategoryEntity().getName())
                            .build())
                    .colorEntity(ColorResponse.builder()
                            .name(productEntity.getColorEntity().getName())
                            .build())
                    .sizeEntity(SizeResponse.builder()
                            .name(productEntity.getSizeEntity().getName())
                            .build())
                    .createDate(productEntity.getCreateDate())

                    .listCart(productEntity.getListCart().stream()
                            .map(cartEntity -> CartResponse.builder()
                                    .id(cartEntity.getId())
                                    .quanity(cartEntity.getQuanity())
                                    .createDate(cartEntity.getCreateDate())
                                    .build())
                            .toList())

//                    .listOrder(null)

                    .build()).toList();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
