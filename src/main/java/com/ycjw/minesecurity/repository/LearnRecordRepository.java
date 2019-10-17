package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.LearnRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnRecordRepository extends JpaRepository<LearnRecord,String> {

}
