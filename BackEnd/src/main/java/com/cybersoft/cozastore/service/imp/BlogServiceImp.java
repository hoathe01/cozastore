package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.payload.request.TagRequest;
import com.cybersoft.cozastore.payload.response.BlogResponse;
import com.cybersoft.cozastore.payload.response.DateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BlogServiceImp {

    void clearCacheBlog();
    List<BlogResponse> getListBlog();

    List<BlogResponse> getPagination(int index, int quantity, int category);

    List<BlogResponse> getBlogByYear(int begin, int end);

    List<BlogResponse> getBlogByKeyWord(String key);

    BlogResponse getBlog(int id);

    boolean addBlog(BlogRequest blogRequest, MultipartFile image);

    boolean deleteBlog(int id);

    boolean updateBlog(BlogRequest blogRequest, MultipartFile image);
}
