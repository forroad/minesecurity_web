package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.enums.ResultStateEnum;
import com.ycjw.minesecurity.model.TrainPlan;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.objectfrom.TrainPlanFrom;
import com.ycjw.minesecurity.service.TrainPlanService;
import com.ycjw.minesecurity.utils.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainPlanController {

    @Autowired
    private TrainPlanService trainPlanService;

    @ApiOperation("【创建一个培训通知】")
    @PostMapping("/create_one_trainplan")
    public Response createOnePlan(@Valid TrainPlanFrom planFrom, BindingResult bindingResult) throws Exception{
        TrainPlan result=trainPlanService.createOnePlan(planFrom);
        if (result==null){
            LogUtil.logger.error("【创建培训安排】----失败---planFrom={"+
                         planFrom.toString()+"}");
            return new Response(ResultStateEnum.RESULT_STATE_FAILURE.getMessage(),null);
        }
        LogUtil.logger.error("【创建培训安排】----成功---planFrom={"+
                planFrom.toString()+"}");
        return new Response(ResultStateEnum.RESULT_STATE_SUCCESS.getMessage(),result);
    }

    @ApiOperation("查询所有培训日期在昨天以后的通知")
    @GetMapping(value = "/findAll_effect_plan")
    public Response findAllEffectPlan() throws Exception{
        List<TrainPlan> trainPlanList=trainPlanService.findAllEffectivePlan();
        if (trainPlanList==null){
            return new Response(ResultStateEnum.RESULT_STATE_FAILURE.getMessage(),null);
        }
        else {
            return new Response(ResultStateEnum.RESULT_STATE_SUCCESS.getMessage(),trainPlanList);
        }
    }

    @ApiOperation("查询符合条件的培训通知")
    @GetMapping("findTraining")
    public Response findTraining() throws Exception{
        List<TrainPlan> trainPlans =trainPlanService.findTraining();
        return new Response(ResultStateEnum.RESULT_STATE_SUCCESS.getMessage(),trainPlans);
    }


}
