package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.enums.ExamRecordStateEnum;
import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.*;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.objectfrom.ExamFrom;
import com.ycjw.minesecurity.service.ExamRecordService;
import com.ycjw.minesecurity.service.ExamService;
import com.ycjw.minesecurity.service.PaperService;
import com.ycjw.minesecurity.service.SelectionQuestionService;
import com.ycjw.minesecurity.utils.JsonUtil;
import com.ycjw.minesecurity.utils.LogUtil;
import com.ycjw.minesecurity.viewObjects.PaperView;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.patterns.ExactAnnotationFieldTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ExamRecordService examRecordService;

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
            //System.out.println("id"+idList.toString());
            //查询题目列表
            selectionQuestions=questionService.findSomeQuestionsNotUsedInPaper(idList,10,0);
            //创建保存
            //System.out.println("q:"+selectionQuestions.toString());
            paperView= paperService.createOnePaper(selectionQuestions);
            paperId=paperView.getPaperId();
        }
        catch (Exception e){
            LogUtil.logger.error("【创建考试对象】---失败---生成卷子失败  paperNiew=");
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


    @ApiOperation("【开始考试】")
    @PostMapping(value = "start_exam")
    public Response startExam(@RequestParam(required = true,value = "phone")String phone,
                              @RequestParam(required = true,value = "examId")String examId) throws Exception{
        //TODO 验证参数
        if (phone==null || examId==null){
            LogUtil.logger.error("【开始考试】--参数错误-- phone="+ phone+",examId="+examId);
            return new Response("失败",null);
        }
        //判断是否已经参加过考试
        //TODO 如果允许用户中途离开需要改变逻辑
        ExamRecord examRecord=null;
        try {
           examRecord=examRecordService.findByPhoneAndExamId(phone,examId);
        }
        catch (Exception e){
            LogUtil.logger.error("【开始考试】--系统错误--查询记录出错 phone="+ phone+",examId="+examId+"\n"+ e.getMessage());
            return new Response("失败",null);
        }
        if (examRecord!=null && examRecord.getEndTime() != null){
            LogUtil.logger.info("【开始考试】--系统错误--用户已经完成考试 phone="+ phone+",examId="+examId);
            return new Response("失败",null);
        }
        //创建考试记录
        try {
            examRecord=examRecordService.createOneExamRecord(phone,examId);
        }
        catch (Exception e){
            LogUtil.logger.error("【开始考试】--系统错误--创建考试记录失败 examRecord="+examRecord.toString());
            return new Response("失败",null);
        }
        //分发试卷
        Exam exam=examService.findOneById(examId);
        if (exam==null){
            LogUtil.logger.error("【开始考试】--参数错误--examId不正确 examId="+examId);
            return new Response("失败",null);
        }
        Paper paper=paperService.findOneById(exam.getPaperId());
        if (paper==null){
            LogUtil.logger.error("【开始考试】--参数错误--paperId不正确 examId="+examId+"paperId="+exam.getPaperId());
            return new Response("失败",null);
        }
        List<String> idList= (List<String>) JsonUtil.toObject(paper.getQuestionList(),List.class);
        //查询题目
        List<SelectionQuestion> questionList=questionService.findListByIdIn(idList);
        if (questionList==null || questionList.isEmpty()){
            LogUtil.logger.error("【开始考试】--数据错误--查询试题失败 examId="+examId+"paperId="+exam.getPaperId()+
                    "questionIdList="+idList.toString());
            return new Response("失败",null);
        }
        LogUtil.logger.info("【开始考试】--成功--phone="+phone+", examId="+examId);
        return new Response("成功",questionList);


   }


    @ApiOperation("模拟考试")
    @GetMapping("virtual_exam")
    public Response virtual_exam(){
        return new Response("获取试题成功",questionService.findVirtualExamQuestion());
    }

   @ApiOperation("【结束考试】")
   @PostMapping(value = "finished_exam")
   public Response finishedExam(@RequestParam(required = true,value = "phone")String phone,
                                @RequestParam(required = true,value = "examId")String examId,
                                @RequestParam(required = true,value = "grade") float grade ) throws Exception{
        //验证参数
       ExamRecord record=examRecordService.findByPhoneAndExamId(phone,examId);
       if (phone==null ||examId==null || grade<0){
           LogUtil.logger.error("【结束考试】--参数错误-- phone="+ phone+",examId="+examId+", grade="+grade);
       }
       //TODO examRecord参数错误也要提交错误信息吗
       if (record==null){
          record= examRecordService.createOneExamRecord(phone,examId);
       }
       //更新记录字段
       record.setGrade(grade);
       record.setEndTime(new Date());
       record.setHasFinished(ExamRecordStateEnum.HAS_FINISHED.getCode());
       try {
           examRecordService.update(record);
       }
       catch (Exception e){
           LogUtil.logger.error("【结束考试】--考试记录数据更新失败--examRecord="+record.toString()+"\n"+e.getStackTrace()+"\n"+e.getCause());
       }
       LogUtil.logger.info("【结束考试】--phone="+phone+", examId="+examId+", examRecordId="+record.toString());
       return new Response("成功",record);
   }

   @ApiOperation("查询考试信息")
    @GetMapping("findExamById")
    public Response findExamById(@RequestParam("examId") String examId) throws Exception {
        return new Response("查询成功",examService.findOneById(examId));
   }

    @ApiOperation("查询最近六次考试成绩")
    @GetMapping("findExamScore")
    public Response findExamScore(@RequestParam("telephone") String telephone) throws Exception {
        return new Response("查询成功",examService.findExamScore(telephone));
    }

}//controller
