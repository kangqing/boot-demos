package com.yunqing.demoweblog.log;

import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 * @author kangqing
 * @since 2020/11/10 10:59
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {

    String operSystem() default ""; // 操作系统

    String operModule() default ""; // 操作模块

    String operType() default "";  // 操作类型

    String operDesc() default "";  // 操作说明
}
