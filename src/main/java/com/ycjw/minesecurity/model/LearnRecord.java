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

}
