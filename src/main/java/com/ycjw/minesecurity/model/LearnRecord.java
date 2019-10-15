package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class LearnRecord {

    /**
     * 学习记录id
     */
    @Id
    private String learnRecordId;

    /**
     * 用户id
     */
    private String UserId;

    /**
     * 本次学习开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;


    /**
     * 学习时长
     */
    private int learnPeriod;

}
