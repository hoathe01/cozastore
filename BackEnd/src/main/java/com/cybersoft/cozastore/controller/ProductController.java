package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestPart ProductRequest productRequest, @RequestParam MultipartFile file){
        boolean isSuccess = productServiceImp.addProduct(productRequest,file);
        BaseResponse response = new BaseResponse(200, isSuccess ? "Thêm Thành Công" : "Thêm Thất Bai", isSuccess);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam int id) {
        boolean isSuccess = productServiceImp.deleteProduct(id);
        BaseResponse response = new BaseResponse(200, isSuccess ? "Xóa Thành Công" : "Xóa Thất Bai", isSuccess);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
