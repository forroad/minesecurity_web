package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.model.CompletedQuestion;
import com.ycjw.minesecurity.model.SelectionQuestion;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.objectfrom.SelectionQuestionFrom;
import com.ycjw.minesecurity.repository.CompletedQuestionRepository;
import com.ycjw.minesecurity.repository.SelectionQuestionRepository;
import com.ycjw.minesecurity.service.SelectionQuestionService;
import com.ycjw.minesecurity.utils.KeyUtil;
import com.ycjw.minesecurity.utils.LogUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class SelectionQuestionServiceImpl implements SelectionQuestionService {

    @Autowired
    private SelectionQuestionRepository questionRepository;

    @Autowired
    private CompletedQuestionRepository completedQuestionRepository;

    @Override
    public SelectionQuestion saveOne(SelectionQuestion selectionQuestion) throws Exception {
        return questionRepository.save(selectionQuestion);
    }

    @Override
    public List<SelectionQuestion> findAll() throws Exception {
        return questionRepository.findAll();
    }

    /**
     * 创建一个选择题
     * @param questionFrom
     * @return
     * @throws Exception
     */
    @Override
    public Response createOneQuestion(SelectionQuestionFrom questionFrom) throws Exception{
        SelectionQuestion question=new SelectionQuestion();
        BeanUtils.copyProperties(questionFrom,question);
        question.setQuestionId(KeyUtil.getUniqueKey_20());

        SelectionQuestion result=saveOne(question);
        if (result==null){
            LogUtil.logger.error("【创建选择题】---保存失败--question={"+question.toString()+"}");
            return new Response("失败",null);
        }
        LogUtil.logger.error("【创建选择题】---保存成功--questionId={"+question.getQuestionId()+"}");
        return new Response("成功",result);
    }

    /**
     * 查询某个人还未刷过的题
     * @param phoneNum
     * @return
     * @throws Exception
     */
    @Override
    public List<SelectionQuestion> findAllNoCompleted(String phoneNum) throws Exception{
       List<CompletedQuestion> completedQuestionList=completedQuestionRepository.findAllByPhoneNum(phoneNum);
       List<String> questionIdList=new LinkedList<String>();
        Iterator<CompletedQuestion> iterator=completedQuestionList.iterator();
        while (iterator.hasNext()){
            questionIdList.add(iterator.next().getQuestionId());
        }
        if (questionIdList.isEmpty()){
            return questionRepository.findAll();
        }
        else {
            return questionRepository.findAllByQuestionIdNotIn(questionIdList);
        }
    }




}
