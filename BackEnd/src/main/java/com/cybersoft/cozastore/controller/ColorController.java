package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("color")
public class ColorController {
    @Autowired
    private ColorServiceImp colorServiceImp;
    @GetMapping
    private ResponseEntity<?> getListColor(){
        BaseResponse response = new BaseResponse(200,"Danh sách Màu", colorServiceImp.getListColor());
        return ResponseEntity.ok(response);
    }
}
