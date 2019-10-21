package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.model.SelectionQuestion;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.service.PaperService;
import com.ycjw.minesecurity.service.SelectionQuestionService;
import com.ycjw.minesecurity.utils.LogUtil;
import com.ycjw.minesecurity.viewObjects.PaperView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;
    @Autowired
    private SelectionQuestionService questionService;


    @ApiOperation("【创建一张卷子】")
    @PostMapping(value = "/create_one_paper")
    public Response createOnePaper(@RequestParam(required = false,defaultValue = "10",value = "size")int size){

        List<String> idList=null;
        List<SelectionQuestion> selectionQuestions=null;
        PaperView paperView=null;
        try {
            //查询所有出现过的题目id
           idList= paperService.findAllQuestionIdsUsedInPaper();
            //查询题目列表
            if (idList.isEmpty()){
                selectionQuestions=questionService.findAll();
            }
            else {
                selectionQuestions=questionService.findSomeQuestionsNotUsedInPaper(idList,size,0);
            }
            //创建保存
           paperView= paperService.createOnePaper(selectionQuestions);
        }
        catch (Exception e){
            return new Response("失败",null);
        }
        LogUtil.logger.info("【创建试卷】--成功---paperView="+paperView.toString());
        return new Response("成功",paperView);
    }

}
