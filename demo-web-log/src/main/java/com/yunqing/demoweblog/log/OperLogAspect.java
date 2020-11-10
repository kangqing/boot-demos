package com.yunqing.demoweblog.log;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yx
 * @since 2020/11/10 14:32
 */
@Aspect
@Component
public class OperLogAspect {

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.yunqing.demoweblog.log.OperLog)")
    public void operLogPointCut() {
    }

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.yunqing.demoweblog.controller..*.*(..))")
    public void operExceptionLogPointCut() {
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "operLogPointCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes)
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OperationLog operlog = new OperationLog();
        try {
            operlog.setId(IdUtil.simpleUUID()); // 主键ID

            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperLog opLog = method.getAnnotation(OperLog.class);
            if (opLog != null) {
                String operSystem = opLog.operSystem();
                String operModule = opLog.operModule();
                String operType = opLog.operType();
                String operDesc = opLog.operDesc();
                operlog.setSystemName(operSystem);
                operlog.setModuleName(operModule); // 操作模块
                operlog.setOperType(operType); // 操作类型
                operlog.setContent(operDesc); // 操作描述
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;

            operlog.setReqMethod(methodName); // 请求方法
            operlog.setMethodType(Objects.requireNonNull(request).getMethod());

            // 请求的参数
            Map<String, String> rtnMap = convertMap(Objects.requireNonNull(request).getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSONUtil.toJsonStr(rtnMap);

            operlog.setReqParam(params); // 请求参数
            operlog.setResponseResult(JSONUtil.toJsonStr(keys)); // 返回结果
            // TODO 获取当前用户,获取当前用户手机
            operlog.setUserId("u1"); // 请求用户ID
            operlog.setMobile("111");
            operlog.setIp(NetUtil.getLocalhost().getHostAddress()); // 请求IP
            operlog.setUri(request.getRequestURI()); // 请求URI
            operlog.setCreateTime(LocalDateTime.now()); // 创建时间
            operlog.setLogType("O");

            //operationLogService.insert(operlog);
            System.out.println(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(operlog)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "operExceptionLogPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes)
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        ExceptionLog excepLog = new ExceptionLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            excepLog.setId(IdUtil.simpleUUID());
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            // 请求的参数
            Map<String, String> rtnMap = convertMap(Objects.requireNonNull(request).getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSONUtil.toJsonStr(rtnMap);
            excepLog.setReqParam(params); // 请求参数
            excepLog.setReqMethod(methodName); // 请求方法名
            excepLog.setExcName(e.getClass().getName()); // 异常名称
            excepLog.setExcMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
            // TODO--------------------
            excepLog.setLogType("E");
            excepLog.setUserId("u1"); // 操作员ID
            excepLog.setMobile("111"); // 操作员名称
            excepLog.setUri(request.getRequestURI()); // 操作URI
            excepLog.setIp(NetUtil.getLocalhost().getHostAddress()); // 操作员IP
            excepLog.setCreateTime(LocalDateTime.now()); // 发生异常时间
            OperLog opLog = method.getAnnotation(OperLog.class);
            if (opLog != null) {
                String operSystem = opLog.operSystem();
                String operModule = opLog.operModule();
                excepLog.setSystemName(operSystem);
                excepLog.setModuleName(operModule); // 操作模块
            }
            System.out.println(excepLog);
            //exceptionLogService.insert(excepLog);

        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, String> convertMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stet : elements) {
            sb.append(stet).append("\n");
        }
        return exceptionName + ":" + exceptionMessage + "\n\t" + sb.toString();
    }

}
