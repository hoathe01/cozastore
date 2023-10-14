package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.dto.RoleDTO;
import com.cybersoft.cozastore.dto.UserDTO;
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
    public List<UserDTO> listUser() {
        List<UserEntity> entityList = userRepository.findAll();
        return entityList.stream()
                .map(userEntity -> UserDTO.builder()
                        .email(userEntity.getEmail())
                        .username(userEntity.getUsername())
                        .role(new RoleDTO(userEntity.getRole().getName(),null))
                        .build()).toList();
    }
}
