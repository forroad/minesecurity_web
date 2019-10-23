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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.*;

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


    /**
     * 查询某页还未出现在过试题中的数个题目
     * @param questionIdList  已经使用过的题目
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<SelectionQuestion> findSomeQuestionsNotUsedInPaper(List<String > questionIdList,int size,int page) throws Exception {
        if (questionIdList==null || questionIdList.isEmpty()){
            return questionRepository.findAll();
        }
        Pageable pageable= PageRequest.of(page,size);
        //当questionIdList为空时，下面也会查出空
        Page<SelectionQuestion> questionPage=questionRepository.findByQuestionIdNotIn(questionIdList,pageable);
        return questionPage.getContent();
    }

    @Override
    public List<SelectionQuestion> findListByIdIn(List<String> questionIdList) throws Exception {
        return questionRepository.findAllByQuestionIdIn(questionIdList);
    }

    @Override
    public List<SelectionQuestion> findVirtualExamQuestion() {
        List<SelectionQuestion> questions = questionRepository.findAll();
        List<SelectionQuestion> return_questions = new ArrayList<>();
        Random random = new Random();
        int maxSum = questions.size() > 10?10:questions.size();
        for(int i = 0;i < maxSum;i++){
            int index = random.nextInt(questions.size());
            return_questions.add(questions.remove(index));
        }
        return return_questions;
    }

    @Override
    public List<SelectionQuestion> findTenQuestion() {
        List<SelectionQuestion> questions = questionRepository.finTenQuestion();
        if(questions == null){
            questions = new ArrayList<>();
        }
        return questions;
    }
}
