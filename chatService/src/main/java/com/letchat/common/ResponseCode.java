package com.letchat.common;

import lombok.Getter;

/**
 * @author alice
 */
@Getter
public enum ResponseCode {
    /**
     * code 错误码
     * message 错误信息
     * **/
    SUCCESS(200,"SUCCESS"),
    SEVER_ERROR(500,"SEVER ERROR"),
    PARAMETER_ERROR(500,"PARAMETER ERROR"),
    PAGE_NOT_FOUND(404,"PAGE NOT FOUND");

    private int code;
    private String message;

    private ResponseCode(int code,String message){
        this.code = code;
        this.message = message;
    }
}
