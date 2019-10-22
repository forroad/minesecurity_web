package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.service.TrainingBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("training_back")
public class TrainingBackController {
    private TrainingBackService trainingBackService;

    @Autowired
    public TrainingBackController(TrainingBackService trainingBackService) {
        this.trainingBackService = trainingBackService;
    }

    @ApiOperation("添加培训反馈")
    @PostMapping("add")
    public Response addTrainingBack(@RequestParam("telephone") String telephone,
            @RequestParam("content") String content) throws ExceptionZyc{
        return new Response("添加成功",trainingBackService.addTrainingBack(telephone, content));
    }

    @ApiOperation("添加培训反馈")
    @GetMapping("find")
    public Response findTrainingBack(@RequestParam("telephone") String telephone) throws ExceptionZyc{
        return new Response("查询成功",trainingBackService.findTrainingBackByTelephone(telephone));
    }
}
