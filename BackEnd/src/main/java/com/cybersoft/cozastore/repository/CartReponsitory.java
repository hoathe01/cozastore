package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartReponsitory extends JpaRepository<CartEntity,Integer> {
}
