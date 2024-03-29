package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.CommentRequest;
import com.cybersoft.cozastore.payload.response.CommentResponse;
import com.cybersoft.cozastore.service.imp.CommentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentServiceImp commentServiceImp;
    @PostMapping("add")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        boolean isSuccess = commentServiceImp.addComments(commentRequest);
        BaseResponse baseResponse = new BaseResponse(200, isSuccess ? "Thêm Thành Công": "Thêm Thất Bại",isSuccess);
        return ResponseEntity.ok(baseResponse);
    }
    @GetMapping("/id={id}/size={size}")
    public ResponseEntity<?> getListComment(@PathVariable int id,@PathVariable int size){
        List<CommentResponse> commentResponseList = commentServiceImp.getListComments(id,size);
        BaseResponse baseResponse = new BaseResponse(200, "Danh Sách Bình Luận",commentResponseList);
        return ResponseEntity.ok(baseResponse);
    }
}
