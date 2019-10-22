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

    public SelectionQuestionFrom() {
    }

    public SelectionQuestionFrom(String questionDescribe, int isMutiAnswer, String optionA, String optionB, String optionC, String optionD, String rightOptions) {
        this.questionDescribe = questionDescribe;
        this.isMutiAnswer = isMutiAnswer;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.rightOptions = rightOptions;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }

    public int getIsMutiAnswer() {
        return isMutiAnswer;
    }

    public void setIsMutiAnswer(int isMutiAnswer) {
        this.isMutiAnswer = isMutiAnswer;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getRightOptions() {
        return rightOptions;
    }

    public void setRightOptions(String rightOptions) {
        this.rightOptions = rightOptions;
    }

    @Override
    public String toString() {
        return "SelectionQuestionFrom{" +
                "questionDescribe='" + questionDescribe + '\'' +
                ", isMutiAnswer=" + isMutiAnswer +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", rightOptions='" + rightOptions + '\'' +
                '}';
    }
}
