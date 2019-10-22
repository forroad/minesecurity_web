package com.ycjw.minesecurity.objectfrom;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class TrainPlanFrom {

    /**
     * 通知标题
     */
    @NotNull
    @NotBlank
    private String planName;

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

    /**
     * 通知类型
     */
    private int planType;

    public TrainPlanFrom() {
    }

    public TrainPlanFrom(@NotNull @NotBlank String planName, @NotNull @NotBlank String trainContent, @NotNull String trainTime, @NotNull @NotBlank int planType) {
        this.planName = planName;
        this.trainContent = trainContent;
        this.trainTime = trainTime;
        this.planType = planType;
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

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getPlanType() {
        return planType;
    }

    public void setPlanType(int planType) {
        this.planType = planType;
    }

    @Override
    public String toString() {
        return "TrainPlanFrom{" +
                "trainContent='" + trainContent + '\'' +
                ", trainTime='" + trainTime + '\'' +
                '}';
    }


}
