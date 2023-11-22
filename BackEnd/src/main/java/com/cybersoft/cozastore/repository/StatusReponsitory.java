package com.cybersoft.cozastore.repository;

import com.cybersoft.demo.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusReponsitory extends JpaRepository<StatusEntity, Integer> {

//    Optional<UserEntity> findById(int idStatus);
}
