package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.ExamRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ExamRecordRepository extends JpaRepository<ExamRecord,String> {

    /**
     * 通过电话和考试id
     * @param phoneNum
     * @param examId
     * @return
     */
    ExamRecord findByPhoneNumAndExamId(String phoneNum,String examId);

    /**
     * 通过电话和记录状态查找
     * @param phoneNum
     * @param hasFinished
     * @return
     */
    List<ExamRecord> findAllByPhoneNumAndHasFinished(String phoneNum,int hasFinished);

    /**
     * 分页查询用户的考试记录
     * @param phoneNum
     * @param pageable
     * @return
     */
    Page<ExamRecord> findAllByPhoneNumAndEndTimeNotNull(String phoneNum, Pageable pageable) throws Exception;

    List<ExamRecord> findAllByEndTimeBefore(Date now, Sort sort);
}
