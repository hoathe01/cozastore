package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.imp.ColorServiceImp;
import com.cybersoft.cozastore.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("size")
public class SizeController {
    @Autowired
    private SizeServiceImp sizeServiceImp;
    @GetMapping
    private ResponseEntity<?> getListSize(){
        BaseResponse response = new BaseResponse(200,"Danh sách size", sizeServiceImp.getListSize());
        return ResponseEntity.ok(response);
    }
}
