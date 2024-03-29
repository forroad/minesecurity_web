package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.model.SelectionQuestion;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.objectfrom.SelectionQuestionFrom;

import java.util.List;

public interface SelectionQuestionService {

    SelectionQuestion saveOne(SelectionQuestion selectionQuestion) throws Exception;

    List<SelectionQuestion> findAll()throws Exception;

    Response createOneQuestion(SelectionQuestionFrom questionFrom) throws Exception;

    List<SelectionQuestion> findAllNoCompleted(String phoneNum) throws Exception;

    List<SelectionQuestion> findSomeQuestionsNotUsedInPaper(List<String > questionIdList,int size,int page) throws Exception;

    List<SelectionQuestion> findListByIdIn(List<String> questionIdList)throws Exception;

    List<SelectionQuestion> findVirtualExamQuestion();

    /**
     * 查询出十个不重复的题目
     * @return
     */
    List<SelectionQuestion> findTenQuestion();
}
