package com.yunqing.demojsonresult.exception;

import com.yunqing.demojsonresult.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
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
 * @author yx
 * @date 2020/5/11 12:14
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    JsonResult<String> jsonResult = new JsonResult(false);

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
                .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
        //JsonResult<String> jsonResult = new JsonResult();
        log.info("请求参数异常",collect);
        jsonResult.setResponseCode(HttpStatus.BAD_REQUEST.value());
        jsonResult.setResponseMsg(ex.getMessage());
        return jsonResult;
    }

    /**
     * Bean 校验异常 Validate
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class) //400
    public JsonResult methodArgumentValidationHandler(HttpServletRequest request, MethodArgumentNotValidException exception){
        log.info("异常:" + request.getRequestURI(), exception);
        log.info("请求参数错误！{}",getExceptionDetail(exception),"参数数据："+ showParams(request));
        //JsonResult<String> jsonResult = new JsonResult();
        jsonResult.setResponseCode(HttpStatus.BAD_REQUEST.value());
        if (exception.getBindingResult() != null && !CollectionUtils.isEmpty(exception.getBindingResult().getAllErrors())) {
            jsonResult.setResponseMsg(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else {
            jsonResult.setResponseMsg(exception.getMessage());
        }
        return jsonResult;
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
        //JsonResult<String> jsonResult = new JsonResult();
        Map map=new HashMap();
        if(pe.getBindingResult()!=null){
            List<ObjectError> allErrors = pe.getBindingResult().getAllErrors();
            allErrors.stream().filter(Objects::nonNull).forEach(objectError -> {
                map.put("请求路径："+request.getRequestURI()+"--请求参数："+(((FieldError) ((FieldError) allErrors.get(0))).getField().toString()),objectError.getDefaultMessage());
            });
        }
        jsonResult.setResponseCode(HttpStatus.BAD_REQUEST.value());
        jsonResult.setResponseMsg("请求参数绑定失败");
        jsonResult.setData(map.toString());
        return jsonResult;
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
        //JsonResult<String> jsonResult = new JsonResult();
        jsonResult.setResponseCode(HttpStatus.BAD_REQUEST.value());
        jsonResult.setResponseMsg("该请求路径："+request.getRequestURI()+"下的请求参数不全："+pe.getMessage());
        return jsonResult;
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
        //JsonResult<String> jsonResult = new JsonResult();
        jsonResult.setResponseCode(HttpStatus.BAD_REQUEST.value());
        jsonResult.setResponseMsg("请求方式不正确");
        return jsonResult;
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
        //JsonResult<String> jsonResult = new JsonResult();
        jsonResult.setResponseCode(HttpStatus.BAD_REQUEST.value());
        jsonResult.setResponseMsg(pe.getMessage());
        return jsonResult;
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
        Map<String,Object> map = new HashMap<String,Object>();
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
