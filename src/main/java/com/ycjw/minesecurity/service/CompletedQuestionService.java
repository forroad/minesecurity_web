package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.model.CompletedQuestion;
import org.springframework.web.bind.annotation.RequestParam;

public interface CompletedQuestionService {

    CompletedQuestion saveOne(CompletedQuestion question) throws Exception;

    CompletedQuestion completedOneQuestion(String phone, String questionId ) throws Exception;

}
