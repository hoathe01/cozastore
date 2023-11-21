package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.entity.CommentEntity;
import com.cybersoft.cozastore.payload.request.CommentRequest;
import com.cybersoft.cozastore.payload.response.CommentResponse;
import com.cybersoft.cozastore.payload.response.DateResponse;
import com.cybersoft.cozastore.repository.CommentRepository;
import com.cybersoft.cozastore.service.imp.CommentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<CommentResponse> getListComments(int id, int size) {
        try {
            List<CommentEntity> getListCommentEntity = commentRepository.findAllByBlogEntity_Id(id);
            if (size >= getListCommentEntity.size()) {
                return getListCommentEntity.stream().map(comment -> CommentResponse.builder()
                        .name(comment.getName())
                        .content(comment.getContent())
                        .createDate(new DateResponse(comment.getCreateDate()))
                        .build()).toList();
            }
            return getListCommentEntity.subList(0, size).stream().map(comment -> CommentResponse.builder()
                    .name(comment.getName())
                    .content(comment.getContent())
                    .createDate(new DateResponse(comment.getCreateDate()))
                    .build()).toList();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

}
