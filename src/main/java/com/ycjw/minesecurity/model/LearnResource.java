package com.ycjw.minesecurity.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class LearnResource {

    /**
     * 资源id
     */
    @Id
    private String resourceId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源内容
     */
    private String resourcePath;

    /**
     * 资源图片路径
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private Date createTime;


}
