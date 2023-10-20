package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.payload.response.BlogResponse;

import java.util.List;

public interface BlogServiceImp {
    List<BlogResponse> getListBlog();

    boolean addBlog(BlogRequest blogRequest);
}
