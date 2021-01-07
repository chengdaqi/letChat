package com.letchat.common;

import lombok.Data;

/**
 * @author alice
 */
@Data
public class Response<T> {

    /**
     * 响应业务状态
     **/
    private int status;

    /**
     * 响应消息
     **/
    private String msg;

    /**
     * 响应中的数据
     **/
    private T data;

    private Response(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private Response(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private Response(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private Response(T data) {
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> Response<T> success(String msg, T data) {
        return new Response<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }


}
