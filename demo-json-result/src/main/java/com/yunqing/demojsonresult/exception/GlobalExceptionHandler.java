package com.yunqing.demojsonresult.exception;

import cn.hutool.json.JSONUtil;
import com.yunqing.demojsonresult.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * 全局异常处理
 * @author yx
 * @date 2020/5/11 12:14
 */
@ResponseBody
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 统一异常日志打印
     * @param e
     */
    public void logExceptionHandler(Throwable e) {
        final HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();

        log.error("请求路径[{}], 请求方式[{}], 请求入参[{}]", uri, method, JSONUtil.toJsonStr(parameterMap), e);

    }

    /**
     * 方法参数校验异常 Validate
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult handleValidationException(ConstraintViolationException ex) {
        logExceptionHandler(ex);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
    }

    /**
     * Bean 校验异常 Validate
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonResult methodArgumentValidationHandler(MethodArgumentNotValidException ex){
        logExceptionHandler(ex);
        if (ex.getBindingResult() != null && !CollectionUtils.isEmpty(ex.getBindingResult().getAllErrors())) {
            return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else {
            return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        }
    }

    /**
     * 绑定异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public JsonResult bindException(BindException ex) {
        logExceptionHandler(ex);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), "请求参数绑定失败");
    }


    /**
     * 访问接口参数不全
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        logExceptionHandler(ex);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), "请求参数不全：" + ex.getMessage());
    }

    /**
     * Http 请求方法不支持异常
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logExceptionHandler(ex);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), "请求方式不正确");
    }

    /**
     * 自定义异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public JsonResult baseException(BaseException ex) {
        logExceptionHandler(ex);
        return JsonResult.fail(ex.getErrorCode(), ex.getMessage());
    }

    /**
     * 其他异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public JsonResult otherException(Exception ex) {
        logExceptionHandler(ex);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
    }
}
