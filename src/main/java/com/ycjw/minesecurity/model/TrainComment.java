package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TrainComment {

    /**
     * 反馈信息id
     */
    @Id
    private String trainCommentId;

    /**
     * 反馈内容
     */
    private String trainCommentContent;

    /**
     * 对应的培训安排id
     */
    private String trainPlanId;

    /**
     * 用户id
     */
    private String userId;

}
