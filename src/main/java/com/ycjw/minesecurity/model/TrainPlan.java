package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TrainPlan {

    /**
     * 培训通知id
     */
    @Id
    private String trainId;

    /**
     * 培训通知内容
     */
    private String trainContent;

}
