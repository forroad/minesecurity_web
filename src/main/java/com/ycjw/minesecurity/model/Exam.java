package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Exam {
    /**
     * id
     */
    @Id
    private String examId;

    /**
     * 考试主题
     */
    private String examTitle;

    /**
     * 报名开始截至时间
     */
    private Date examDeadline;

    /**
     * 报考试开始时间
     */
    private Date examStartTime;

    /**
     * 考试截至时间
     */
    private Date examEndTime;

    public Exam() {
    }

    public Exam(String examId, String examTitle, Date examDeadline, Date examStartTime, Date examEndTime) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.examDeadline = examDeadline;
        this.examStartTime = examStartTime;
        this.examEndTime = examEndTime;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Date getExamDeadline() {
        return examDeadline;
    }

    public void setExamDeadline(Date examDeadline) {
        this.examDeadline = examDeadline;
    }

    public Date getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(Date examStartTime) {
        this.examStartTime = examStartTime;
    }

    public Date getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(Date examEndTime) {
        this.examEndTime = examEndTime;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId='" + examId + '\'' +
                ", examTitle='" + examTitle + '\'' +
                ", examDeadline=" + examDeadline +
                ", examStartTime=" + examStartTime +
                ", examEndTime=" + examEndTime +
                '}';
    }
}
