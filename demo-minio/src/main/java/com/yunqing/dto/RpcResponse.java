package com.yunqing.dto;

import java.io.Serializable;

/**
 * @author kangqing
 * @since 2023/8/4 08:28
 */
public class RpcResponse<T> implements Serializable {

    private String msg;
    private T data;

    private RpcResponse(String msg) {
        this.msg = msg;
    }

    private RpcResponse(T data) {
        this.data = data;
    }

    private RpcResponse(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public static <T> RpcResponse<T> success() {
        return new RpcResponse<>("success");
    }

    public static <T> RpcResponse<T> success(T data) {
        return new RpcResponse<>("success", data);
    }

    public static <T> RpcResponse<T> failure() {
        return new RpcResponse<>("failure");
    }

    public static <T> RpcResponse<T> failure(T data) {
        return new RpcResponse<>("failure", data);
    }




}
