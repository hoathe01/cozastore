package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.imp.TagServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    private TagServiceImp tagServiceImp;
    @GetMapping
    private ResponseEntity<?> getListTag(){
        BaseResponse response = new BaseResponse(200,"Danh Sách Tag", tagServiceImp.getListTag());
        return ResponseEntity.ok(response);
    }
}
