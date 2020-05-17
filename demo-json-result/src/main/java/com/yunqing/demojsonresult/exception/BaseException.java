package com.yunqing.demojsonresult.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 自定义异常类
 * @Author yunqing
 * @Data 2020/5/17 21:31
 */
@Getter
@Setter
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
        this.setErrorMsg(msg);
    }
    public BaseException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
