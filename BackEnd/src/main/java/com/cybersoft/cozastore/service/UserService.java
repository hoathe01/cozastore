package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.RoleEntity;
import com.cybersoft.cozastore.payload.request.UserRequest;
import com.cybersoft.cozastore.payload.response.RoleResponse;
import com.cybersoft.cozastore.payload.response.UserResponse;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Lấy danh sách User
    @Override
    public List<UserResponse> listUser() {
        try {
            List<UserEntity> entityList = userRepository.findAll();
            return entityList.stream()
                    .map(u -> UserResponse.builder()
                            .id(u.getId())
                            .email(u.getEmail())
                            .username(u.getUsername())
                            .role(new RoleResponse(u.getRole().getName(), null))
                            .build()).toList();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    //SignUp -> Thêm User
    @Override
    public boolean addUser(UserRequest userRequest) {
        try {
            userRepository.save(UserEntity.builder()
                    .email(userRequest.getEmail())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .username(userRequest.getUsername())
                    .role(new RoleEntity(userRequest.getRole(), null, null, null))
                    .createDate(new Date())
                    .build());
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    //Xóa User
    @Override
    public boolean deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    //sửa User
    @Override
    public boolean updateUser(UserRequest userRequest) {
        try {
            if (userRequest.getId() == 0) {
                return false;
            }
            userRepository.save(UserEntity.builder()
                    .id(userRequest.getId())
                    .email(userRequest.getEmail())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .username(userRequest.getUsername())
                    .role(new RoleEntity(userRequest.getRole(), null, null, null))
                    .build());
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

}
