package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.payload.request.RoleRes;
import com.cybersoft.cozastore.payload.request.UserRes;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserRes> listUser() {
        List<UserEntity> entityList = userRepository.findAll();
        return entityList.stream()
                .map(userEntity -> UserRes.builder()
                        .email(userEntity.getEmail())
                        .username(userEntity.getUsername())
                        .role(new RoleRes(userEntity.getRole().getName(),null))
                        .build()).toList();
    }
}
