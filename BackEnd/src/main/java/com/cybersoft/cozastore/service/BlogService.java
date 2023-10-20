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
            List<BlogEntity> blogEntityList = blogRepository.findAll();
            return blogEntityList.stream().map(blogEntity -> BlogResponse.builder()
                    .id(blogEntity.getId())
                    .title(blogEntity.getTitle())
                    .image(blogEntity.getImage())
                    .content(blogEntity.getContent())
                    .createDate(blogEntity.getCreateDate())
                    .user(UserResponse.builder()
                            .email(blogEntity.getUserEntity().getEmail())
                            .username(blogEntity.getUserEntity().getUsername())
                            .role(RoleResponse.builder().name(blogEntity.getUserEntity().getRole().getName()).build())
                            .build())
                    .listComment(blogEntity.getListComment().stream().map(commentEntity -> CommentResponse.builder()
                            .name(commentEntity.getName())
                            .email(commentEntity.getEmail())
                            .content(commentEntity.getContent())
                            .createDate(commentEntity.getCreateDate())
                            .build()).toList())
                    .listTag(blogEntity.getListTag().stream().map(tagEntity -> TagResponse.builder()
                                    .name(tagEntity.getTag().getName())
                                    .build())
                            .toList())
                    .build()).toList();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
