package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 考试安排类
 */
@Data
@Entity
public class TestPlan {

    /**
     * 考试安排id
     */
    @Id
    private String  testId;

    /**
     * 考试安排说明
     */
    private String  testDescribe;

    /**
     * 本次考试的试卷id
     */
    private String  paperId;

    /**
     * 考试状态
     *-1：未到考试时间（仅仅显示）
     * 0：进行中（目前可进入参加考试）
     * 1：已经结束
     */
    private int state;

    /**
     * 考试类型
     * 0:正式考试 1：模拟考试
     */
    private int testType;

    /**
     * 考试开始时间
     */
    private Date startTime;

    /**
     * 考试结束时间
     */
    private Date endTime;

}
