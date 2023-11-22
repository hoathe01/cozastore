package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.OrdersEntity;
import com.cybersoft.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdersReponsitory extends JpaRepository<OrdersEntity,Integer> {
}
