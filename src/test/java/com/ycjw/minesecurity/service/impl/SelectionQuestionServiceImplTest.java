package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.model.SelectionQuestion;
import com.ycjw.minesecurity.service.SelectionQuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SelectionQuestionServiceImplTest {

    @Autowired
    private SelectionQuestionService selectionQuestionService;

    @Test
    public void saveOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void createOneQuestion() {
    }

    @Test
    public void findAllNoCompleted() {
    }

    @Test
    public void findSomeQuestionsNotUsedInPaper() throws Exception{
        List<String> list= Arrays.asList("157132616311417");
        List<SelectionQuestion> re=selectionQuestionService.findSomeQuestionsNotUsedInPaper(list,5,0);
        return;
    }
}