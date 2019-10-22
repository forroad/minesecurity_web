package com.ycjw.minesecurity.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
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

    /**
     * 试卷题目List的json字符串
     */
    private String questionList;

    public Paper() {
    }

    public Paper(String paperId, int testPeriod, float grade, Date createTime, String questionList) {
        this.paperId = paperId;
        this.testPeriod = testPeriod;
        this.grade = grade;
        this.createTime = createTime;
        this.questionList = questionList;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public int getTestPeriod() {
        return testPeriod;
    }

    public void setTestPeriod(int testPeriod) {
        this.testPeriod = testPeriod;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getQuestionList() {
        return questionList;
    }

    public void setQuestionList(String questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "paperId='" + paperId + '\'' +
                ", testPeriod=" + testPeriod +
                ", grade=" + grade +
                ", createTime=" + createTime +
                ", questionList='" + questionList + '\'' +
                '}';
    }

}

