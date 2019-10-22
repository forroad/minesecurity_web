package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
public class LearnRecord {

    /**
     * 学习记录id
     */
    @Id
    private String learnRecordId;

    /**
     * 用户id
     */
    private String phoneNum;

    /**
     * 学习资源id
     */
    private String resourceId;

    /**
     * 学习日期
     */
    private Date learnDate;

    /**
     * 学习时长
     */
    private int timeLong;

    public LearnRecord(String learnRecordId, String phoneNum, String resourceId, Date learnDate, int timeLong) {
        this.learnRecordId = learnRecordId;
        this.phoneNum = phoneNum;
        this.resourceId = resourceId;
        this.learnDate = learnDate;
        this.timeLong = timeLong;
    }

    public LearnRecord() {
    }

    public String getLearnRecordId() {
        return learnRecordId;
    }

    public void setLearnRecordId(String learnRecordId) {
        this.learnRecordId = learnRecordId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Date getLearnDate() {
        return learnDate;
    }

    public void setLearnDate(Date learnDate) {
        this.learnDate = learnDate;
    }

    public int getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(int timeLong) {
        this.timeLong = timeLong;
    }

    @Override
    public String toString() {
        return "LearnRecord{" +
                "learnRecordId='" + learnRecordId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", learnDate=" + learnDate +
                ", timeLong=" + timeLong +
                '}';
    }
}
