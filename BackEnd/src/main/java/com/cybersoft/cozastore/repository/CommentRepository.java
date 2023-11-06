package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
    void deleteAllByBlogEntity_Id(int id);
}
