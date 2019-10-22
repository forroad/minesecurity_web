package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.model.Paper;
import com.ycjw.minesecurity.model.SelectionQuestion;
import com.ycjw.minesecurity.viewObjects.PaperView;

import java.util.List;

public interface PaperService {

    Paper saveOne(Paper paper) throws Exception;

    List<Paper> findAll() throws Exception;

    List<String> findAllQuestionIdsUsedInPaper() throws Exception;

    PaperView createOnePaper(List<SelectionQuestion> questionList) throws Exception;

    Paper findOneById(String id) throws Exception;

}
