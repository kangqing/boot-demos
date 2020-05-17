package com.yunqing.demojsonresult.exception;

import com.yunqing.demojsonresult.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * 仅仅在GlobalExceptionHandler不存在的时候才启用
 * @author yx
 * @date 2020/5/11 12:14
 */
@RestControllerAdvice
@Slf4j
@ConditionalOnMissingBean(GlobalExceptionHandler.class)
public class GlobalExceptionHandler {

    /**
     * 方法参数校验异常 Validate
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult handleValidationException(HttpServletRequest request, ConstraintViolationException ex) {
        log.error("异常:" + request.getRequestURI(), ex);
        String collect = ex.getConstraintViolations().stream().filter(Objects::nonNull)
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
        log.info("请求参数异常",collect);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
    }

    /**
     * Bean 校验异常 Validate
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonResult methodArgumentValidationHandler(HttpServletRequest request, MethodArgumentNotValidException exception){
        log.info("异常:" + request.getRequestURI(), exception);
        log.info("请求参数错误！{}",getExceptionDetail(exception),"参数数据："+ showParams(request));
        if (exception.getBindingResult() != null && !CollectionUtils.isEmpty(exception.getBindingResult().getAllErrors())) {
            return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else {
            return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage());
        }
    }

    /**
     * 绑定异常
     * @param request
     * @param pe
     * @return
     */
    @ExceptionHandler(BindException.class)
    public JsonResult bindException(HttpServletRequest request, BindException pe) {
        log.error("异常:" + request.getRequestURI(), pe);
        Map map=new HashMap();
        if(pe.getBindingResult()!=null){
            List<ObjectError> allErrors = pe.getBindingResult().getAllErrors();
            allErrors.stream().filter(Objects::nonNull).forEach(objectError -> {
                map.put("请求路径："+request.getRequestURI()+"--请求参数："+(((FieldError) allErrors.get(0)).getField()),
                        objectError.getDefaultMessage());
            });
        }
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), "请求参数绑定失败");
    }


    /**
     * 访问接口参数不全
     * @param request
     * @param pe
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult missingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException pe) {
        log.error("异常:" + request.getRequestURI(), pe);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), "该请求路径：" +
                request.getRequestURI() + "下的请求参数不全："+pe.getMessage());
    }

    /**
     * HttpRequestMethodNotSupportedException
     * @param request
     * @param pe
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult httpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException pe) {
        log.error("异常:" + request.getRequestURI(), pe);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), "请求方式不正确");
    }

    /**
     * 自定义异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public JsonResult baseException(HttpServletRequest request, BaseException ex) {
        log.error("自定义异常:" + request.getRequestURI(), ex);
        return JsonResult.fail(ex.getErrorCode(), ex.getMessage());
    }


    /**
     * 其他异常
     * @param request
     * @param pe
     * @return
     */
    @ExceptionHandler(Exception.class)
    public JsonResult otherException(HttpServletRequest request, Exception pe) {
        log.error("异常:" + request.getRequestURI(), pe);
        return JsonResult.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), pe.getMessage());
    }

    /**
     * 异常详情
     * @param e
     * @return
     */
    private String getExceptionDetail(Exception e) {
        StringBuilder stringBuffer = new StringBuilder(e.toString() + "\n");
        StackTraceElement[] messages = e.getStackTrace();
        Arrays.stream(messages).filter(Objects::nonNull).forEach(stackTraceElement -> {
            stringBuffer.append(stackTraceElement.toString() + "\n");
        });
        return stringBuffer.toString();
    }

    /**
     * 请求参数
     * @param request
     * @return
     */
    public String showParams(HttpServletRequest request) {
        StringBuilder stringBuilder=new StringBuilder();
        Enumeration paramNames = request.getParameterNames();
        stringBuilder.append("----------------参数开始-------------------");
        stringBuilder.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(Objects.nonNull(paramNames)){
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length >0) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        stringBuilder.append("参数名:").append(paramName).append("参数值:").append(paramValue);
                    }
                }
            }
        }
        stringBuilder.append("----------------参数结束-------------------");
        return stringBuilder.toString();
    }
}
