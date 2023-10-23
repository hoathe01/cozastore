package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping
    public ResponseEntity<?> getListProduct(){
        List<?> listProduct = productServiceImp.getListProduct();

        BaseResponse response = new BaseResponse(200, "Danh Sách Product", listProduct);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
