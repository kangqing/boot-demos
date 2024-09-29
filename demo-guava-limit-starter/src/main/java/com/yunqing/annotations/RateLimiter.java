package com.yunqing.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 * 使用 google guava 令牌桶算法
 * @author kangqing
 * @since 2024/9/12 19:20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    // 限流目标，"ip" 或 "appKey"
    String target() default "ip";

    // 限流值，如果未声明则为 -1，表示使用配置文件中的限流值
    double limit() default -1;
}
