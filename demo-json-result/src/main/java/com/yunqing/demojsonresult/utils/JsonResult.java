package com.yunqing.demojsonresult.utils;

import com.yunqing.demojsonresult.exception.BaseException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kangqing
 * @since 2020/5/9 13:58
 * 统一返回实体
 */
@Getter
@Builder
@NoArgsConstructor
public class JsonResult<T> implements Serializable {

    private Boolean status;
    private String code;
    private String msg;
    private T data;

    private JsonResult(boolean status) {
        this.status = status;
        this.code = status ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = status ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }

    private JsonResult(boolean status, String msg) {
        this.status = status;
        this.code = status ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = msg;
    }

    /**
     * 仅返回错误信息可以自定义code返回码，成功一律200
     * @param code
     * @param msg
     */
    private JsonResult(String code, String msg) {
        this.status = false;
        this.code = code;
        this.msg = msg;
    }

    private JsonResult(boolean status, T data) {
        this.status = status;
        this.code = status ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = status ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.data = data;
    }

    private JsonResult(boolean status, String msg, T data) {
        this.status = status;
        this.code = status ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = msg;
        this.data = data;
    }

    private JsonResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private JsonResult(boolean status, String code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResult<T> success(){
        return new JsonResult<>(true);
    }

    public static <T> JsonResult<T> success(String msg){
        return new JsonResult<T>(true, msg);
    }

    public static <T> JsonResult<T> success(T data){
        return new JsonResult<T>(true, data);
    }

    public static <T> JsonResult<T> success(String msg, T data){
        return new JsonResult<T>(true, msg, data);
    }

    public static <T> JsonResult<T> fail(){
        return new JsonResult<T>(false);
    }

    public static <T> JsonResult<T> fail(String msg){
        return new JsonResult<T>(false, msg);
    }

    public static <T> JsonResult<T> fail(String code, String msg){
        return new JsonResult<T>(code, msg);
    }

    public static <T> JsonResult<T> fail(String code, String msg, T data){
        return new JsonResult<T>(false, code, msg, data);
    }

    public static <T> JsonResult<T> noStatus(String code, String msg, T data){
        return new JsonResult<T>(code, msg, data);
    }

    /**
     * 构造一个异常的API返回
     *
     * @param t   异常
     * @param <T> {@link BaseException} 的子类
     * @return ApiResponse
     */
    public static <T extends BaseException> JsonResult<T> ofException(T t) {
        return noStatus(t.getErrorCode(), t.getErrorMsg(), null);
    }


}
