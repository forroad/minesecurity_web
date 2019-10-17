package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.model.CompletedQuestion;
import com.ycjw.minesecurity.repository.CompletedQuestionRepository;
import com.ycjw.minesecurity.service.CompletedQuestionService;
import com.ycjw.minesecurity.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CompletedQuestionServiceImpl implements CompletedQuestionService {

    @Autowired
    private CompletedQuestionRepository completedQuestionRepository;

    @Override
    public CompletedQuestion saveOne(CompletedQuestion question) throws Exception {
        return completedQuestionRepository.save(question);
    }

    /**
     * 创建一个已经学习的题目记录
     * @param phone
     * @param questionId
     * @return
     * @throws Exception
     */
    @Override
    public CompletedQuestion completedOneQuestion(String phone, String questionId ) throws Exception{
       CompletedQuestion completedQuestion=new CompletedQuestion();
       //设置属性
       completedQuestion.setCompletedQuestionId(KeyUtil.getUniqueKey_20());
       completedQuestion.setPhoneNum(phone);
       completedQuestion.setQuestionId(questionId);
       //保存
       return saveOne(completedQuestion);
    }

}
