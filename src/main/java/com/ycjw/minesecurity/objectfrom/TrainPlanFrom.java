package com.ycjw.minesecurity.objectfrom;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
    private String trainTime;

    public TrainPlanFrom() {
    }

    public TrainPlanFrom(String trainContent, String trainTime) {
        this.trainContent = trainContent;
        this.trainTime = trainTime;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }


    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    @Override
    public String toString() {
        return "TrainPlanFrom{" +
                "trainContent='" + trainContent + '\'' +
                ", trainTime='" + trainTime + '\'' +
                '}';
    }


}
