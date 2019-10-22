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

    public CompletedQuestion() {
    }

    public CompletedQuestion(String completedQuestionId, String questionId, String phoneNum) {
        this.completedQuestionId = completedQuestionId;
        this.questionId = questionId;
        this.phoneNum = phoneNum;
    }

    public String getCompletedQuestionId() {
        return completedQuestionId;
    }

    public void setCompletedQuestionId(String completedQuestionId) {
        this.completedQuestionId = completedQuestionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "CompletedQuestion{" +
                "completedQuestionId='" + completedQuestionId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
