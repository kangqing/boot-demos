package com.yunqing.demojsonresult.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yx
 * @date 2020/5/9 13:58
 * 统一返回实体
 */
@Setter
@Getter
@NoArgsConstructor
public class JsonResult<T> implements Serializable {

    private Boolean status;
    private Integer responseCode;
    private String responseMsg;
    private T data;

    public JsonResult(boolean status) {
        this.status = status;
        this.responseCode = status ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.responseMsg = status ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }

    public JsonResult(boolean status, ResultCode resultEnum) {
        this.status = status;
        this.responseCode = status ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.responseMsg = status ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
    }

    public JsonResult(boolean status, T data) {
        this.status = status;
        this.responseCode = status ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.responseMsg = status ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.data = data;
    }

    public JsonResult(boolean status, ResultCode resultEnum, T data) {
        this.status = status;
        this.responseCode = status ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.responseMsg = status ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
        this.data = data;
    }
}
