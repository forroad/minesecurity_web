package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.LearnResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnResourceDao extends JpaRepository<LearnResource,String> {

}
