package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.enums.ExamRecordStateEnum;
import com.ycjw.minesecurity.model.Exam;
import com.ycjw.minesecurity.model.ExamRecord;
import com.ycjw.minesecurity.repository.ExamRecordRepository;
import com.ycjw.minesecurity.service.ExamRecordService;
import com.ycjw.minesecurity.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamRecordServiceImpl implements ExamRecordService {

    @Autowired
    private ExamRecordRepository examRecordRepository;

    @Override
    public ExamRecord saveOne(ExamRecord examRecord) throws Exception {
        return examRecordRepository.save(examRecord);
    }

    @Override
    public ExamRecord findByPhoneAndExamId(String phoneNum,String examId) throws Exception {
        return examRecordRepository.findByPhoneNumAndExamId(phoneNum,examId);
    }

    @Override
    public ExamRecord update(ExamRecord newExamRecord) throws Exception {
        return saveOne(newExamRecord);
    }

    @Override
    public List<ExamRecord> findListByPhoneAndState(String phone, ExamRecordStateEnum stateEnum) throws Exception {
        return examRecordRepository.findAllByPhoneNumAndHasFinished(phone,stateEnum.getCode());
    }


    @Override
    public ExamRecord createOneExamRecord(String phone, String examId) throws Exception {
        ExamRecord examRecord=new ExamRecord();
        examRecord.setExamRecordId(KeyUtil.getUniqueKey_20());
        examRecord.setExamId(examId);
        examRecord.setHasFinished(ExamRecordStateEnum.NO_FINISHED.getCode());
        examRecord.setPhoneNum(phone);
        examRecord.setStartTime(new Date());
        return saveOne(examRecord);
    }

    @Override
    public ExamRecord findById(String id) throws Exception {
        return examRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<ExamRecord> findSomeExamRecordByPhone(String phone, int size, int page) throws Exception {
        Sort sort=new Sort(Sort.Direction.DESC,"endTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<ExamRecord> recordPage=examRecordRepository.findAllByPhoneNum(phone,pageable);
        List<ExamRecord> recordList=recordPage.getContent();
        return recordList;
    }
}
