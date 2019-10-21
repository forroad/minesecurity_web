package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.model.Paper;
import com.ycjw.minesecurity.model.SelectionQuestion;
import com.ycjw.minesecurity.repository.PaperRepository;
import com.ycjw.minesecurity.repository.SelectionQuestionRepository;
import com.ycjw.minesecurity.service.PaperService;
import com.ycjw.minesecurity.utils.JsonUtil;
import com.ycjw.minesecurity.utils.KeyUtil;
import com.ycjw.minesecurity.utils.LogUtil;
import com.ycjw.minesecurity.viewObjects.PaperView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private SelectionQuestionRepository selectionQuestionRepository;

    @Override
    public Paper saveOne(Paper paper) throws Exception {
        return paperRepository.save(paper);
    }

    @Override
    public List<Paper> findAll() throws Exception{
        return paperRepository.findAll();
    }

    /**
     * 获取所有卷子中出现过的题目的id
     * @return
     * @throws Exception
     */
    @Override
    public synchronized List<String> findAllQuestionIdsUsedInPaper() throws Exception {
        List<Paper> paperList=paperRepository.findAll();
        if (paperList==null){
            return null;
        }
        List<String> questionIdList=new LinkedList<>();
        Iterator<Paper> iterator=paperList.iterator();
        while (iterator.hasNext()){
            List<String> ids= JsonUtil.toObject(iterator.next().getQuestionList(),List.class);
            questionIdList.addAll(ids);
        }
        return questionIdList;
    }

    /**
     * 创建一份卷子
     * @param questionList
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaperView createOnePaper(List<SelectionQuestion> questionList) throws Exception {
        Paper paper=new Paper();
        paper.setPaperId(KeyUtil.getUniqueKey_15());
        paper.setCreateTime(new Date());

        Iterator<SelectionQuestion> iterator=questionList.iterator();
        List<String> questionIds=new LinkedList<>();
        float grad=0f;
        while (iterator.hasNext()){
            SelectionQuestion question=iterator.next();
            questionIds.add(question.getQuestionId());
        }
        System.out.println("j:"+questionIds);
        String questionListJson=JsonUtil.toJson(questionIds);
        System.out.println("js:"+questionListJson);
        paper.setQuestionList(questionListJson);
        paper.setGrade(100f);
        try {
            paperRepository.save(paper);
        }
        catch (Exception e){
            LogUtil.logger.error("【创建试卷】---失败--保存失败  paper="+
                                         paper.toString());
            return null;
        }
        PaperView paperView=new PaperView();
        BeanUtils.copyProperties(paper,paperView);
        paperView.setQuestionList(questionList);
        return paperView;
    }



}
