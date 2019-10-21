package com.ycjw.minesecurity.objectfrom;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ExamFrom {


        /**
         * 考试主题
         */
        @NotNull
        @NotBlank
        private String examTitle;

        /**
         * 报名截至时间
         */
        @NotNull
        private String examDeadline;

        /**
         * 报考试开始时间
         */
        @NotNull
        private String examStartTime;

        /**
         * 考试截至时间
         */
        @NotNull
        private String examEndTime;

    public ExamFrom() {
    }

    public ExamFrom(String examTitle, String examDeadline, String examStartTime, String examEndTime) {
         this.examTitle = examTitle;
         this.examDeadline = examDeadline;
         this.examStartTime = examStartTime;
         this.examEndTime = examEndTime;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getExamDeadline() {
        return examDeadline;
    }

    public void setExamDeadline(String examDeadline) {
        this.examDeadline = examDeadline;
    }

    public String getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(String examStartTime) {
        this.examStartTime = examStartTime;
    }

    public String getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(String examEndTime) {
        this.examEndTime = examEndTime;
    }

    @Override
    public String toString() {
        return "ExamFrom{" +
                "examTitle='" + examTitle + '\'' +
                ", examDeadline=" + examDeadline +
                ", examStartTime=" + examStartTime +
                ", examEndTime=" + examEndTime +
                '}';
    }
}
