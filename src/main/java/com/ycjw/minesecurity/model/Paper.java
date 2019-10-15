package com.ycjw.minesecurity.model;

import lombok.Data;
import org.apache.catalina.LifecycleState;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * 试卷类
 */
@Entity
@Data
public class Paper {

    /**
     * 试卷id
     */
    @Id
    private String paperId;

    /**
     * 该试卷的考试时长
     */
    private int testPeriod;

    /**
     * 改试卷的总分
     */
    private float grade;

    /**
     * 该试卷的创建时间
     */
    private Date createTime;

}
