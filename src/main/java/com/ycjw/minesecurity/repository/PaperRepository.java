package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends JpaRepository<Paper, String> {

}
