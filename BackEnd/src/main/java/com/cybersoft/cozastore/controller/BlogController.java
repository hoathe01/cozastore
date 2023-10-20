package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.payload.request.TagRequest;
import com.cybersoft.cozastore.service.imp.BlogServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
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

    @PostMapping
    public ResponseEntity<?> addBlog(@RequestBody BlogRequest blogRequest) {
        boolean isSuccess = blogServiceImp.addBlog(blogRequest);

        blogRequest.getListTag().stream().forEach(tagRequest -> log.error(tagRequest.getId() + ""));

        BaseResponse response = new BaseResponse(200, isSuccess ? "Thêm Thành Công" : "Thêm Thất Bai", isSuccess);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
