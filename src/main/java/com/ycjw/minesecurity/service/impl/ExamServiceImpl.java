package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.Exam;
import com.ycjw.minesecurity.model.ExamRecord;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.objectfrom.ExamFrom;
import com.ycjw.minesecurity.repository.ExamRecordRepository;
import com.ycjw.minesecurity.repository.ExamRepository;
import com.ycjw.minesecurity.service.ExamService;
import com.ycjw.minesecurity.utils.DateUtil;
import com.ycjw.minesecurity.utils.KeyUtil;
import com.ycjw.minesecurity.utils.LogUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamRecordRepository examRecordRepository;

    @Override
    public Exam saveOne(Exam exam) throws Exception {
        return examRepository.save(exam);
    }

    /**
     * 创建一个考试安排对象
     * @param examFrom
     * @return
     * @throws Exception
     */
    @Override
    public Exam createOneExam(ExamFrom examFrom,String paperId) throws Exception {
        Exam newExam=new Exam();
        try{
            newExam.setExamId(KeyUtil.getUniqueKey_15());
            newExam.setExamDeadline(DateUtil.strToDate_minute(examFrom.getExamDeadline()));
            newExam.setExamStartTime(DateUtil.strToDate_minute(examFrom.getExamStartTime()));
            newExam.setExamEndTime(DateUtil.strToDate_minute(examFrom.getExamEndTime()));
            newExam.setExamTitle(examFrom.getExamTitle());
            newExam.setPaperId(paperId);
        }
        catch (Exception e){

            LogUtil.logger.error("【创建考试记录】---失败---日期参数转换错误 examFrom="
                                  +examFrom.toString()+"\n"+e.getMessage()
                                 );
            throw new ExceptionZyc(new Response("日期格式转换错误",examFrom));
        }

        System.out.println("E:"+newExam.toString());
        return saveOne(newExam);
    }

    @Override
    public List<Exam> findExamListBeforeEnd() throws Exception {
        return examRepository.findAllByExamEndTimeAfter(DateUtil.convert2Day());
    }

    @Override
    public List<Exam> findAll() throws Exception {
        return examRepository.findAll();
    }

    @Override
    public Exam findOneById(String id) throws Exception {
        return examRepository.findById(id).orElse(null);
    }

    @Override
    public List<Float> findExamScore(String telephone) throws ExceptionZyc {
        List<Float> scores = new ArrayList<>();
        List<ExamRecord> examRecords = examRecordRepository.findAllByEndTimeBefore(new Date(),new Sort(Sort.Direction.DESC,"endTime"));
        int index = examRecords.size() > 6?6:examRecords.size();
        for(int i = 0;i < index;i++){
            scores.add(examRecords.get(i).getGrade());
        }
        if(scores.size() < 6){
            index = 6 - scores.size();
        }
        for(int i = 0;i < index;i++) scores.add(0.0f);
        return scores;
    }
}//calss
