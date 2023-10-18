package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.UserRequest;
import com.cybersoft.cozastore.payload.response.UserResponse;

import java.util.List;

public interface UserServiceImp {
    List<UserResponse> listUser();
    boolean addUser(UserRequest userRequest);
    boolean deleteUser(int id);
    boolean updateUser(UserRequest userRequest);

}
