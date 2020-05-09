package com.yunqing.demojsonresult.utils;

/**
 * @author yx
 * @date 2020/5/9 14:00
 * 返回体构造工具
 */
public class ResultTool {

    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }
}
