package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity,Integer> {
    @Query("FROM blog WHERE YEAR(createDate) between ?1 and ?2 order by createDate desc")
    List<BlogEntity> findByYear(int begin, int end);
}
