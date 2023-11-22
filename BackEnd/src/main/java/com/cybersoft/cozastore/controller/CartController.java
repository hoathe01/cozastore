package com.cybersoft.cozastore.controller;


import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.CartRequest;
import com.cybersoft.cozastore.service.imp.CartServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartServiceImp cartServiceImp;
    @GetMapping("/{idUser}")
    public ResponseEntity<?> getCartList(@PathVariable int idUser){
        List<?> listCart = cartServiceImp.getCartResponse(idUser);
        BaseResponse baseResponse = new BaseResponse(200, "ListCart",listCart);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> insertCart(@RequestBody CartRequest cartRequest){
        boolean isSuccess = cartServiceImp.insertCart(cartRequest);
        BaseResponse baseResponse = new BaseResponse(200, isSuccess?"inserted":"insert failed",isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?> updateCart(@RequestBody CartRequest cartRequest){
        boolean isSuccess = cartServiceImp.updateCart(cartRequest);
        BaseResponse baseResponse = new BaseResponse(200, isSuccess?"updated":"update failed",isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable int id){
        boolean isSuccess = cartServiceImp.deleteCart(id);
        BaseResponse baseResponse = new BaseResponse(200, isSuccess?"deleted":"deleted",isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
