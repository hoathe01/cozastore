package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.CommentRequest;

public interface CommentServiceImp {
    boolean addComments(CommentRequest commentRequest);
}
