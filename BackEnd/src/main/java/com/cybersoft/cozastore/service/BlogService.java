package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.payload.response.*;
import com.cybersoft.cozastore.repository.BlogRepository;
import com.cybersoft.cozastore.service.imp.BlogServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BlogService implements BlogServiceImp {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<BlogResponse> getListBlog() {
        try {
            List<BlogEntity> blogEntities = blogRepository.findAll();
            return blogEntities.stream().map(blog -> BlogResponse.builder()
                    .id(blog.getId())
                    .title(blog.getTitle())
                    .image(blog.getImage())
                    .content(blog.getContent())
                    .createDate(blog.getCreateDate())
                    .user(UserResponse.builder()
                            .email(blog.getUserEntity().getEmail())
                            .username(blog.getUserEntity().getUsername())
                            .role(new RoleResponse(blog.getUserEntity().getRole().getName(), null))
                            .build())
                    .listComment(blog.getListComment().stream()
                            .map(comment -> CommentResponse.builder()
                                    .name(comment.getName())
                                    .email(comment.getEmail())
                                    .content(comment.getContent())
                                    .createDate(comment.getCreateDate())
                                    .build())
                            .toList())
                    .listTag(blog.getListTag().stream().map(tag -> TagResponse.builder()
                                    .name(tag.getTag().getName())
                                    .build())
                            .toList())
                    .build()).toList();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
