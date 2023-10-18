package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
@ControllerAdvice
public class HelloController {
    @GetMapping
    public ResponseEntity<?> getGreeting(){
        BaseResponse response = new BaseResponse(200, "", "Hello World");

        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<?> postGreeting(){
        BaseResponse response = new BaseResponse(200, "", "Hello World");
        return ResponseEntity.ok(response);
    }
}
