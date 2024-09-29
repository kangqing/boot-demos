package com.yunqing.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author kangqing
 * @since 2024/9/12 21:32
 */
@Aspect
@Component
public class RateLimiterAspect {

    private final RateLimiterConfigService configService;
    private final HttpServletRequest request;
    // 使用 Guava Cache 替换 ConcurrentHashMap，缓存10分钟内未访问的限流器
    private final Cache<String, RateLimiter> rateLimiters = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)  // 设置过期时间为10分钟
            .build();

    public RateLimiterAspect(RateLimiterConfigService configService, HttpServletRequest request) {
        this.configService = configService;
        this.request = request;
    }

    @Around("@annotation(rateLimiter)")
    public Object applyRateLimiter(ProceedingJoinPoint joinPoint, com.yunqing.annotations.RateLimiter rateLimiter) throws Throwable {
        String target = rateLimiter.target();
        String key;
        double limit;

        // 基于 IP 或 AppKey 的限流逻辑
        if ("appKey".equalsIgnoreCase(target)) {
            key = request.getHeader("AppKey");
            // 如果注解中配置了 limit，则使用注解的限流数，否则从配置文件中读取
            limit = rateLimiter.limit() > 0 ? rateLimiter.limit() : configService.getRateLimitByAppKey(key);
        } else {
            key = request.getRemoteAddr();  // 获取请求 IP 地址
            // 如果注解中配置了 limit，则使用注解的限流数，否则从配置文件中读取
            limit = rateLimiter.limit() > 0 ? rateLimiter.limit() : configService.getRateLimitByIp(key);
        }

        // 获取或创建限流器
        RateLimiter limiter = rateLimiters.get(key, () -> RateLimiter.create(limit));

        // 限流处理
        if (limiter.tryAcquire()) {
            return joinPoint.proceed();  // 通过限流，执行目标方法
        } else {
            throw new RuntimeException("Too many requests, please try again later.");
        }
    }
}
