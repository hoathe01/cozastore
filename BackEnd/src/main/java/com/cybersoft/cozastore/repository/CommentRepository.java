package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
    void deleteAllByBlogEntity_Id(int id);

    @Query("FROM comment c WHERE c.blogEntity.id = ?1 ORDER BY c.createDate DESC")
    List<CommentEntity> findAllByBlogEntity_Id(int id);
}
