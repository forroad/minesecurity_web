package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.TrainingBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingBackDao extends JpaRepository<TrainingBack,String> {
    List<TrainingBack> findByTelephone(String telephone);
}
