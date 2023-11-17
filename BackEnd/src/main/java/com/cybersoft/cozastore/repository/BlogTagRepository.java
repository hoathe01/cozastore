package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.entity.BlogTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagRepository extends JpaRepository<BlogTagEntity, Integer> {
    void deleteAllByBlog_Id(int id);
    List<BlogTagEntity> findAllByTag_Id(int id);
}
