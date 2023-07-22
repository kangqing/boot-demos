package com.yunqing.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author kangqing
 * @since 2023/7/22 14:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedissonLock {

    /**
     * key 的前缀，默认取全限定名（包名＋方法名）
     * @return
     */
    String prefixKey() default "";

    /**
     * EL 表达式
     * @return
     */
    String key();

    /**
     * 锁的等待时间，默认 -1，不等待直接失败，redisson 默认也是 -1
     * @return
     */
    int waitTime() default -1;

    /**
     * 等待时间单位，默认毫秒
     * @return
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
}
