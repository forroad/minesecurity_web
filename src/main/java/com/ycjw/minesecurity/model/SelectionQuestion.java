package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SelectionQuestion {

    /**
     * 题目编号
     */
    @Id
    private String questionId;

    /**
     * 题目描述
     */
    private String questionDescribe;

    /**
     *是否为多选，0-否，1-是
     */
    private int isMultiAnswer;

    /**
     * 选项 A
     */
    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    /**
     * 正确答案
     */
    private String rightOptions;


}
