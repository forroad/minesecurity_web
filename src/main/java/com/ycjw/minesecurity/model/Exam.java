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

}
