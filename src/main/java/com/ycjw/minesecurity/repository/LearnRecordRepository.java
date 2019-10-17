package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.LearnRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnRecordRepository extends JpaRepository<LearnRecord,String> {

    //通过电话查找
    List<LearnRecord> findAllByPhoneNum(String phoneNum);
}
