package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.dto.UserDTO;
import com.cybersoft.cozastore.entity.UserEntity;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> listUser();
}
