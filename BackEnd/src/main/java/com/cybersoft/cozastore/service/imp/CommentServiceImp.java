package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.CommentRequest;
import com.cybersoft.cozastore.payload.response.CommentResponse;

import java.util.List;

public interface CommentServiceImp {
    boolean addComments(CommentRequest commentRequest);
    List<CommentResponse> getListComments(int id,int size);
}
