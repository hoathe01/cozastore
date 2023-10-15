package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@ControllerAdvice
public class HelloController {
    @GetMapping
    public ResponseEntity<?> getGreeting(){
        BaseResponse response = new BaseResponse(200, "", "Hello World");
        return ResponseEntity.ok(response);
    }
}
