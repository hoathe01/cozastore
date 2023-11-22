package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CartEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.payload.request.CartRequest;
import com.cybersoft.cozastore.payload.response.CartResponse;
import com.cybersoft.cozastore.repository.CartReponsitory;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.CartServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CartService implements CartServiceImp {
    @Autowired
    CartReponsitory cartReponsitory;
    @Autowired
    ProductRepository productReponsitory;
    @Autowired
    UserRepository userRepository;
    @Override
    public boolean insertCart(CartRequest cartRequest) {
        try {
            cartReponsitory.save(CartEntity.builder()
                    .quanity(cartRequest.getQuantity())
                    .productEntity(ProductEntity.builder().id(cartRequest.getIdProduct()).build())
                    .userEntity(UserEntity.builder().id(cartRequest.getIdUser()).build())
                    .build());
            return true;
        }catch (Exception e){
            log.warn(e.getLocalizedMessage());
            return false;

        }
    }

    @Override
    public boolean updateCart(CartRequest cartRequest) {
        try {
            Optional<CartEntity> cartEntity = cartReponsitory.findById(cartRequest.getId());
            if(cartEntity.isPresent()) {
                cartReponsitory.save(CartEntity.builder()
                        .userEntity(UserEntity.builder().id(cartRequest.getIdProduct()).build())
                        .productEntity(ProductEntity.builder().id(cartRequest.getIdProduct()).build())
                        .quanity(cartRequest.getQuantity())
                        .build());
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            log.warn(e.getLocalizedMessage());
            return false;
        }



    }

    @Override
    public boolean deleteCart(int id) {
        try {
            Optional<CartEntity> cartEntity = cartReponsitory.findById(id);
            if(cartEntity.isPresent()){
                cartReponsitory.deleteById(id);
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            log.warn(e.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public List<CartResponse> getCartResponse(int idUser) {
        try {
            List<CartEntity> cartEntityList = cartReponsitory.findAll();
            return cartEntityList.stream().map(cartEntity -> CartResponse.builder()
                    .id(cartEntity.getId())
                    .quanity(cartEntity.getQuanity())
                    .createDate(cartEntity.getCreateDate())
                    .build()).toList();
        }catch (Exception e) {
            log.warn(e.getLocalizedMessage());
            return null;
        }

    }
}
