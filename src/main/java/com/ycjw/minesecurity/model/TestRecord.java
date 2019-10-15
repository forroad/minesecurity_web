package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户考试记录
 */
@Entity
@Data
public class TestRecord {

    /**
     * 考试记录id
     */
    @Id
    private String recordId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 对应的考试安排id
     */
    private String testPlanId;

    /**
     * 用户在本次考试的分数
     */
    private float grade;

}
