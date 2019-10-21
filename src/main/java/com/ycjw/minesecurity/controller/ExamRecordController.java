package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.model.ExamRecord;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.service.ExamRecordService;
import com.ycjw.minesecurity.utils.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam_record")
public class ExamRecordController {

    @Autowired
    private ExamRecordService examRecordService;

    @ApiOperation("【查询考试用户记录】")
    @PostMapping("/find_someexamrecord_byphone")
    public Response findSomeExamRecordByPhone(@RequestParam(required = true,value = "phone")String phone,
                                              @RequestParam(required = false,defaultValue = "50",value = "size")int size,
                                              @RequestParam(required = false,defaultValue = "0",value = "page")int page){
        //
        List<ExamRecord> recordList=null;
        try {
           recordList= examRecordService.findSomeExamRecordByPhone(phone,size,page);
        }catch (Exception e){
             LogUtil.logger.error("【查询用户考试记录】--失败--系统错误");
             return new Response("失败",null);
        }
        return new Response("成功",recordList);
    }
}
