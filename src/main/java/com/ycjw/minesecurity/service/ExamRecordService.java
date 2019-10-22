package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.enums.ExamRecordStateEnum;
import com.ycjw.minesecurity.model.Exam;
import com.ycjw.minesecurity.model.ExamRecord;

import java.util.List;

public interface ExamRecordService {

    ExamRecord saveOne(ExamRecord examRecord) throws Exception;

    ExamRecord findByPhoneAndExamId(String phoneNum,String examId) throws Exception;

    ExamRecord update(ExamRecord newExamRecord) throws Exception;

    List<ExamRecord> findListByPhoneAndState(String phone, ExamRecordStateEnum stateEnum) throws Exception;

    ExamRecord createOneExamRecord(String phone,String examId) throws Exception;

    ExamRecord findById(String id) throws Exception;

    List<ExamRecord> findSomeExamRecordByPhone(String phone,int size,int page) throws Exception;
}
