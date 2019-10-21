package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.model.TrainPlan;
import com.ycjw.minesecurity.objectfrom.TrainPlanFrom;
import com.ycjw.minesecurity.repository.TrainPlanRepository;
import com.ycjw.minesecurity.service.TrainPlanService;
import com.ycjw.minesecurity.utils.DateUtil;
import com.ycjw.minesecurity.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainPlanServiceImpl implements TrainPlanService {

    @Autowired
    private TrainPlanRepository trainPlanRepository;

    @Override
    public TrainPlan saveOne(TrainPlan trainPlan) {
        return trainPlanRepository.save(trainPlan);
    }

    /**
     * 查找所有的有效期后一天内的培训通知
     * @return
     */
    public List<TrainPlan> findAllEffectivePlan() throws Exception{
        Calendar today=Calendar.getInstance();
        today.setTime(DateUtil.convert2Day());
        int todayNUm=today.get(Calendar.DATE);

        Calendar lastDay=(Calendar.getInstance());
        lastDay.set(Calendar.DATE,todayNUm-2);
        Date last=lastDay.getTime();
        System.out.println(last.toString());
        System.out.println(DateUtil.convert2Day().toString());
        System.out.println((new Date()).toString());
        List<TrainPlan> planList=trainPlanRepository.findAllByTrainTimeAfter(last);
        //planList= planList.stream().sorted().collect(Collectors.toList());
        return planList;
    }

    /**
     * 创建一个培训通知
     * @param planFrom
     * @return
     * @throws Exception
     */
    @Override
    public TrainPlan createOnePlan(TrainPlanFrom planFrom) throws Exception {
        TrainPlan trainPlan=new TrainPlan();
        BeanUtils.copyProperties(planFrom,trainPlan);
        trainPlan.setTrainId(KeyUtil.getUniqueKey_15());
        trainPlan.setCreateTime(new Date());
        return saveOne(trainPlan);
    }


}
