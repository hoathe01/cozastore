package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.BlogTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogTagRepository extends JpaRepository<BlogTagEntity, Integer> {
}
