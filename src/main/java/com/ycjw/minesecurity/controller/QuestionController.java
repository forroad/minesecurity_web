package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.enums.ResultStateEnum;
import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.CompletedQuestion;
import com.ycjw.minesecurity.model.SelectionQuestion;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.objectfrom.SelectionQuestionFrom;
import com.ycjw.minesecurity.service.CompletedQuestionService;
import com.ycjw.minesecurity.service.SelectionQuestionService;
import com.ycjw.minesecurity.utils.LogUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private SelectionQuestionService questionService;

    @Autowired
    private CompletedQuestionService completedQuestionService;

    @ApiOperation("创建一个选择题")
    @PostMapping("/create_one_question")
    public Response createOneQuestion(@Valid SelectionQuestionFrom questionFrom,
                                      BindingResult bindingResult) throws Exception{
        //参数检查
        if (bindingResult.hasErrors()){
            LogUtil.logger.error("【创建选择题】--失败--参数错误--questionFrom={"+questionFrom.toString()+"}");
            throw new ExceptionZyc(new Response("参数错误",null));
        }
        return questionService.createOneQuestion(questionFrom);
    }

    @ApiOperation("查询所有该用户还未刷过的题")
    @GetMapping("/find_all_nocompleted")
    public Response findAllNoCompletedQue(@RequestParam(required = true,value = "phone")String phone) throws Exception{
        List<SelectionQuestion> questionList=questionService.findAllNoCompleted(phone);
        if(questionList.isEmpty()){
            return new Response("暂无题目可刷",null);
        }
        return new Response(ResultStateEnum.RESULT_STATE_SUCCESS.getMessage(),questionList);
    }


    @ApiOperation("创建已学习记录")
    @PostMapping(value = "/completed_one_question")
    public Response completedOneQuestion(@RequestParam(required = true,value = "phone")String phone,
                                         @RequestParam(required = true,value = "questionId")String questionId ) throws Exception{

        CompletedQuestion re=completedQuestionService.completedOneQuestion(phone,questionId);
        if (re==null) {
            LogUtil.logger.error("【创建已学习记录】---失败--phone={" + phone + "}" + "---questionId={" + questionId + "}");
            return new Response(ResultStateEnum.RESULT_STATE_FAILURE.getMessage(), null);
        }
        LogUtil.logger.info("【创建已学习记录】---成功--phone={" + phone + "}" + "---questionId={" + questionId + "}");
        return new Response(ResultStateEnum.RESULT_STATE_SUCCESS.getMessage(), re);
    }

}
