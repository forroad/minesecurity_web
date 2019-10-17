package com.ycjw.minesecurity.objectfrom;

import lombok.Data;

import javax.persistence.Id;

@Data
public class SelectionQuestionFrom {

    /**
     * 题目描述
     */
    private String questionDescribe;

    /**
     *是否为多选，0-否，1-是
     */
    private int isMutiAnswer;

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
