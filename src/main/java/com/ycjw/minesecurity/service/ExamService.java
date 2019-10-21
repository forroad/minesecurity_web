package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.model.Exam;
import com.ycjw.minesecurity.objectfrom.ExamFrom;

import java.util.List;

public interface ExamService {

    Exam saveOne(Exam exam) throws Exception;

    Exam createOneExam(ExamFrom examFrom,String paperId) throws Exception;

    List<Exam> findExamListBeforeEnd() throws Exception;

    List<Exam>  findAll() throws Exception;

    Exam findOneById(String id) throws Exception;

}
