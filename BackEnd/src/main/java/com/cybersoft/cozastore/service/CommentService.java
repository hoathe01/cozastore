package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.entity.CommentEntity;
import com.cybersoft.cozastore.payload.request.CommentRequest;
import com.cybersoft.cozastore.payload.response.CommentResponse;
import com.cybersoft.cozastore.repository.CommentRepository;
import com.cybersoft.cozastore.service.imp.CommentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Slf4j
public class CommentService implements CommentServiceImp {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean addComments(CommentRequest commentRequest) {
        try {

            commentRepository.save(CommentEntity.builder()
                    .name(commentRequest.getName())
                    .email(commentRequest.getEmail())
                    .content(commentRequest.getContent())
                            .blogEntity(BlogEntity.builder().id(commentRequest.getIdBlog()).build())
                    .createDate(new Date())
                    .build());
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }
}
