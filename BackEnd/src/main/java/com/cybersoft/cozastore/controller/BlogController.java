package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogServiceImp blogServiceImp;
    @GetMapping
    public ResponseEntity<?> getListBlog() {
        List<?> listBlog = blogServiceImp.getListBlog();
        BaseResponse response = new BaseResponse(200, "Danh Sách Blog", listBlog);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
