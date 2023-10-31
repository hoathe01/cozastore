package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.UserRequest;
import com.cybersoft.cozastore.payload.response.UserResponse;
import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping
    public ResponseEntity<?> getListUser() {
        List<UserResponse> list = userServiceImp.listUser();
        BaseResponse baseResponse = new BaseResponse(200, "", list);
        return ResponseEntity.ok(baseResponse);
    }

    @DeleteMapping
    public ResponseEntity<?> deletetUser(int id) {
        boolean isSuccess = userServiceImp.deleteUser(id);
        BaseResponse baseResponse = new BaseResponse(200, "", isSuccess);
        return ResponseEntity.ok(baseResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequest userRequest) {
        boolean isSuccess = userServiceImp.updateUser(userRequest);
        BaseResponse baseResponse = new BaseResponse(200, "", isSuccess);
        return ResponseEntity.ok(baseResponse);
    }
}
