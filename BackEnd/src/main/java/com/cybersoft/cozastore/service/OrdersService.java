package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.*;
import com.cybersoft.cozastore.payload.request.OdersRequest;
import com.cybersoft.cozastore.payload.response.OrderReponse;
import com.cybersoft.cozastore.repository.OdersReponsitory;
import com.cybersoft.cozastore.repository.StatusReponsitory;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.OrderServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class OrdersService implements OrderServiceImp {
    @Autowired
    OdersReponsitory odersReponsitory;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusReponsitory statusReponsitory;
    @Override
    public boolean insertOrder(OdersRequest odersRequest) {
        try {
            odersReponsitory.save(OrdersEntity.builder()
                    .id(odersRequest.getId())
                    .statusEntity(StatusEntity.builder().id(odersRequest.getIdStatus()).build())
                    .userEntity(UserEntity.builder().id(odersRequest.getIdUser()).build())
                    .build());
            return true;
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage());
            return false;

        }
    }
    @Override
    public boolean updateOrder(OdersRequest odersRequest) {
        try {
            Optional<OrdersEntity> ordersEntity = odersReponsitory.findById(odersRequest.getId());
            if(ordersEntity.isPresent()) {
                odersReponsitory.save(OrdersEntity.builder()
                        .id(odersRequest.getId())
                        .statusEntity(StatusEntity.builder().id(odersRequest.getIdStatus()).build())
                        .userEntity(UserEntity.builder().id(odersRequest.getIdUser()).build())
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
    public boolean deleteOrder(int idUser) {
        try {
            Optional<OrdersEntity> ordersEntity = odersReponsitory.findById(idUser);
            if(ordersEntity.isPresent()){
                odersReponsitory.deleteById(idUser);
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
    public List<OrderReponse> getOrderRequestList(int idUser) {
        try {
            List<OrdersEntity> ordersEntities = odersReponsitory.findAll();
            Date now = new Date();
            return ordersEntities.stream().map(ordersEntity -> OrderReponse.builder()
                    .idOder(ordersEntity.getId())
                    .idStatus(ordersEntity.getStatusEntity().getId())
                    .createDate(now)
                    .build()).toList();
        }catch (Exception e) {
            log.warn(e.getLocalizedMessage());
            return null;
        }

    }
    }

