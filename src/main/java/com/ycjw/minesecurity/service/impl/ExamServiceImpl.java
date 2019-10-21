package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.Exam;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.objectfrom.ExamFrom;
import com.ycjw.minesecurity.repository.ExamRepository;
import com.ycjw.minesecurity.service.ExamService;
import com.ycjw.minesecurity.utils.DateUtil;
import com.ycjw.minesecurity.utils.KeyUtil;
import com.ycjw.minesecurity.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

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
}//calss
