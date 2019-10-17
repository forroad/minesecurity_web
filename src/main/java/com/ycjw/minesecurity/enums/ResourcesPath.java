package com.ycjw.minesecurity.enums;

import lombok.Getter;

/**
 * 资源上传路径枚举
 */
@Getter
public enum ResourcesPath {
    LEARN_RESOURCE_IMAGE_PATH("C:\\minesecurity\\resourceimg\\"),
    LEARN_RESOURCE_DOC_PATH("C:\\minesecurity\\resourcedoc\\"),
    ;
    private String resourcePrefix;
    ResourcesPath(String resourcePrefix){
        this.resourcePrefix=resourcePrefix;
    }

}
