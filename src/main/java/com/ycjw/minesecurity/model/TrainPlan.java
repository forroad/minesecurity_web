package com.ycjw.minesecurity.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TrainPlan {

    /**
     * 培训通知id
     */
    @Id
    private String trainId;

    /**
     * 培训通知内容
     */
    private String trainContent;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 培训日期
     */
    private Date trainTime;


    public TrainPlan() {
    }

    public TrainPlan(String trainId, String trainContent, Date createTime, Date trainTime) {
        this.trainId = trainId;
        this.trainContent = trainContent;
        this.createTime = createTime;
        this.trainTime = trainTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(Date trainTime) {
        this.trainTime = trainTime;
    }

    @Override
    public String toString() {
        return "TrainPlan{" +
                "trainId='" + trainId + '\'' +
                ", trainContent='" + trainContent + '\'' +
                ", createTime=" + createTime +
                ", trainTime=" + trainTime +
                '}';
    }
}
