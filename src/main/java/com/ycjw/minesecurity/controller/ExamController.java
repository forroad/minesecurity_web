package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.model.Exam;
import com.ycjw.minesecurity.model.SelectionQuestion;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.objectfrom.ExamFrom;
import com.ycjw.minesecurity.service.ExamService;
import com.ycjw.minesecurity.service.PaperService;
import com.ycjw.minesecurity.service.SelectionQuestionService;
import com.ycjw.minesecurity.utils.LogUtil;
import com.ycjw.minesecurity.viewObjects.PaperView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private SelectionQuestionService questionService;

    @ApiOperation("【创建一个考试安排对象】")
    @PostMapping(value = "/create_one_exam")
    public Response createOneExam(@Valid ExamFrom examFrom, BindingResult bindingResult) throws Exception{
        //检查参数
        if(bindingResult.hasErrors()){
            LogUtil.logger.error("【创建考试对象】---失败---参数错误  examFrom={"
                                   +examFrom.toString()
                                    +"}");
            return new Response("参数错误",null);
        }
        List<String> idList=null;
        List<SelectionQuestion> selectionQuestions=null;
        PaperView paperView=null;
        String paperId=null;
        try {
            //查询所有出现过的题目id
            idList= paperService.findAllQuestionIdsUsedInPaper();
            //查询题目列表
            selectionQuestions=questionService.findSomeQuestionsNotUsedInPaper(idList,10,0);
            //创建保存
            paperView= paperService.createOnePaper(selectionQuestions);
            paperId=paperView.getPaperId();
        }
        catch (Exception e){
            LogUtil.logger.error("【创建考试对象】---失败---生成卷子失败  paperNiew="
                    +paperView.toString());
        }
        Exam result=examService.createOneExam(examFrom,paperId);
        if (result==null){
            return new Response("失败",null);
        }
        LogUtil.logger.error("【创建考试对象】---成功---  exam={"
                +result.toString()
                +"}");
        return new Response("成功",result);
    }


    @ApiOperation("【查询所有还未到考试结束时间的考试安排】")
    @GetMapping(value = "/find_exam_list")
    public Response findExamListBeforeEnd() throws Exception{
        List<Exam> examList=examService.findExamListBeforeEnd();
        if(examList==null){
            LogUtil.logger.warn("【查询考试安排】---结果为空---date="+
                    (new Date()).toString());
            return new Response("失败",null);
        }
        return new Response("成功",examList);
    }


}//controller
