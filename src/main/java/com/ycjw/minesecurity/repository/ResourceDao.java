package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.Resource;
import com.ycjw.minesecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends JpaRepository<Resource,String> {
    Resource findByResourceId(String resourceId);
}
