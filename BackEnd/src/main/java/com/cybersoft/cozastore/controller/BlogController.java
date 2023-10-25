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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
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
    public ResponseEntity<?> addBlog(@RequestPart BlogRequest blogRequest, @RequestParam MultipartFile file) {
        blogRequest.getListTag().forEach(tagRequest -> log.info(tagRequest.getId() + ""));
        boolean isSuccess = blogServiceImp.addBlog(blogRequest,file);
        BaseResponse response = new BaseResponse(200, isSuccess ? "Thêm Thành Công" : "Thêm Thất Bai", isSuccess);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteBlog(@RequestParam int id) {
        boolean isSuccess = blogServiceImp.deleteBlog(id);
        BaseResponse response = new BaseResponse(200, isSuccess ? "Xóa Thành Công" : "Xóa Thất Bai", isSuccess);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> updateBlog(@RequestBody BlogRequest blogRequest, @RequestParam MultipartFile file) {
        boolean isSuccess = blogServiceImp.updateBlog(blogRequest,file);
        BaseResponse response = new BaseResponse(200, isSuccess ? "Sửa Thành Công" : "Sửa Thất Bai", isSuccess);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
