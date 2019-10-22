package com.ycjw.minesecurity.enums;

import lombok.Getter;

/**
 * 返回前端的状态字段
 */
@Getter
public enum ResultStateEnum {
    RESULT_STATE_SUCCESS("成功"),
    RESULT_STATE_FAILURE("失败"),

    ;
    private String message;

    ResultStateEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}