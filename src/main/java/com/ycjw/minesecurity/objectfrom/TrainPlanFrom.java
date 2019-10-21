package com.ycjw.minesecurity.objectfrom;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TrainPlanFrom {


    /**
     * 培训通知内容
     */
    @NotNull
    @NotBlank
    private String trainContent;


    /**
     * 培训日期
     */
    @NotNull
    private Date trainTime;

    public TrainPlanFrom() {
    }

    public TrainPlanFrom(String trainContent, Date trainTime) {
        this.trainContent = trainContent;
        this.trainTime = trainTime;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }


    public Date getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(Date trainTime) {
        this.trainTime = trainTime;
    }

    @Override
    public String toString() {
        return "TrainPlanFrom{" +
                "trainContent='" + trainContent + '\'' +
                ", trainTime=" + trainTime +
                '}';
    }
}
