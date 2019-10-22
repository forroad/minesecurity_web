package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.model.TrainPlan;
import com.ycjw.minesecurity.objectfrom.TrainPlanFrom;

import java.util.List;

public interface TrainPlanService {

    TrainPlan saveOne(TrainPlan trainPlan);

    List<TrainPlan> findAllEffectivePlan() throws Exception;

    List<TrainPlan> findTraining() throws Exception;

    TrainPlan createOnePlan(TrainPlanFrom planFrom) throws Exception;

}
