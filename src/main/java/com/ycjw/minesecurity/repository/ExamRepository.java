package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,String> {

    /**
     *查询所有还未到考试及结束时间的考试安排
     * @param today
     * @return
     */
    List<Exam> findAllByExamEndTimeAfter(Date today);
}
