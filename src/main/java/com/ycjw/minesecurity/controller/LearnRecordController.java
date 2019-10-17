package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.service.LearnRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/learn_record")
public class LearnRecordController {

    @Autowired
    private LearnRecordService learnRecordService;

    @ApiOperation("添加学习记录")
    @PostMapping("/create_learn_record")
    public Response createLearnRecord(@RequestParam(required = true,value = "phoneNum") String phoneNum,
                               @RequestParam(required = true,value = "timeLong") int timeLong,
                               @RequestParam(required = true,value = "resourceId") String resourceId) throws Exception{
        return learnRecordService.createLearnRecord(phoneNum,timeLong,resourceId);
    }

    @ApiOperation("统计学习记录")
    @GetMapping(value = "/count_learn_record")
    public Response countLearnRecord(@RequestParam(required = true,value = "phoneNum") String phoneNum) throws Exception{
        return learnRecordService.countLearnRecord(phoneNum);
    }

}
