package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CompletedQuestion {

    /**
     * 题目编号
     */
    @Id
    private String completedQuestionId;

    /**
     * 对应题目编号
     */
    private String questionId;

    /**
     * 用户电话
     */
    private String phoneNum;

}
