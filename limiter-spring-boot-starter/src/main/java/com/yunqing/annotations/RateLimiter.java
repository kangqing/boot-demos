package com.yunqing.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 限流注解
 * 使用令牌桶算法
 * @author kangqing
 * @since 2024/9/12 19:20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    // 限流目标，"ip" 或 "appKey"
    String target() default "ip";

    // 限流值，如果未声明则为 -1，表示使用配置文件中的限流值
    long limit() default -1L;

    // 默认为-1表示不限制
    long totalLimit() default -1L;

    // 访问频率单位，默认为每秒
    TimeUnit unit() default TimeUnit.SECONDS;

    // 接口总限流单位
    TimeUnit totalUnit() default TimeUnit.SECONDS;
}
