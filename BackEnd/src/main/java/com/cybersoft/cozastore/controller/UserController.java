package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.UserRes;
import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;
    @GetMapping
    public ResponseEntity<?> getListUser(){
        List<UserRes> list = userServiceImp.listUser();
        BaseResponse baseResponse = new BaseResponse(200,"",list);
        return ResponseEntity.ok(baseResponse);
    }
}
