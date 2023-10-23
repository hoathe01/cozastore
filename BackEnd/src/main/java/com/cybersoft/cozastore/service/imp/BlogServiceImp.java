package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.payload.request.TagRequest;
import com.cybersoft.cozastore.payload.response.BlogResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogServiceImp {
    List<BlogResponse> getListBlog();

    boolean addBlog(BlogRequest blogRequest, MultipartFile image);

    boolean deleteBlog(int id);

    boolean updateBlog(BlogRequest blogRequest, MultipartFile image);
}
