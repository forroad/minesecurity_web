package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.TrainPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrainPlanRepository extends JpaRepository<TrainPlan,String> {

    /**
     * 查找目前最多培训完一天的通知
     * @param tomorrow
     * @return
     */
    List<TrainPlan> findAllByTrainTimeAfter(Date tomorrow);


    List<TrainPlan> findAllByTrainTimeAfterAndPlanType(Date tomorrow,int planType);
}
