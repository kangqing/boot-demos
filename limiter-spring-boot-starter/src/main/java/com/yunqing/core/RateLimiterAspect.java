package com.yunqing.core;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
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
    private final RedissonClient redissonClient;

    public RateLimiterAspect(RateLimiterConfigService configService, HttpServletRequest request, RedissonClient redissonClient) {
        this.configService = configService;
        this.request = request;
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(rateLimiter)")
    public Object applyRateLimiter(ProceedingJoinPoint joinPoint, com.yunqing.annotations.RateLimiter rateLimiter) throws Throwable {
        String target = rateLimiter.target();
        String methodName = joinPoint.getSignature().toShortString();
        String key;
        long limit;
        long totalLimit = rateLimiter.totalLimit();
        TimeUnit unit = rateLimiter.unit();
        TimeUnit totalUnit = rateLimiter.totalUnit();

        // 基于IP或AppKey的限流逻辑
        if ("appKey".equalsIgnoreCase(target)) {
            String appKey = request.getHeader("AppKey");
            key = methodName + ":appKey:" + appKey;
            limit = rateLimiter.limit() > 0 ? rateLimiter.limit() : configService.getRateLimitByAppKey(appKey);
        } else {
            String ipAddress = request.getRemoteAddr();
            key = methodName + ":ip:" + ipAddress;
            limit = rateLimiter.limit() > 0 ? rateLimiter.limit() : configService.getRateLimitByIp(ipAddress);
        }

        // 获取 Redis 中的令牌桶
        Bucket sourceLimiter = getOrCreateBucketFromRedis(key, limit, unit);

        // 全局限流处理
        if (totalLimit > 0) {
            String globalKey = methodName + ":global";
            Bucket globalLimiter = getOrCreateBucketFromRedis(globalKey, totalLimit, totalUnit);
            if (!globalLimiter.tryConsume(1)) {
                throw new RuntimeException("Too many requests globally, please try again later.");
            }
        }

        // 来源限流处理
        if (sourceLimiter.tryConsume(1)) {
            return joinPoint.proceed();  // 通过限流，执行目标方法
        } else {
            throw new RuntimeException("Too many requests from this source, please try again later.");
        }
    }

    // 从 Redis 中获取或创建令牌桶
    private Bucket getOrCreateBucketFromRedis(String key, long limit, TimeUnit timeUnit) {
        RBucket<Bucket> redisBucket = redissonClient.getBucket("rateLimiter:" + key);
        Bucket bucket = redisBucket.get();
        long expireTimeMillis = calculateExpireTimeMillis(timeUnit);
        if (bucket == null) {
            bucket = createBucket(limit, timeUnit);
            // 初次创建时设置过期时间
            redisBucket.set(bucket, expireTimeMillis, TimeUnit.MILLISECONDS);
        } else {
            // 刷新过期时间
            redisBucket.expire(expireTimeMillis, TimeUnit.MILLISECONDS);
        }
        return bucket;
    }

    // 动态计算过期时间
    private long calculateExpireTimeMillis(TimeUnit timeUnit) {
        if (timeUnit == TimeUnit.SECONDS) {
            return TimeUnit.SECONDS.toMillis(60);  // 秒级限流，设置60秒过期时间
        } else if (timeUnit == TimeUnit.MINUTES) {
            return TimeUnit.MINUTES.toMillis(5);  // 分钟级限流，设置5分钟过期时间
        } else {
            return timeUnit.toMillis(2);  // 默认设置2倍的过期时间
        }
    }

    // 使用Bucket4j创建令牌桶
    public Bucket createBucket(long limit, TimeUnit timeUnit) {
        Duration refillDuration = Duration.ofMillis(timeUnit.toMillis(1));  // 根据时间单位设置填充周期
        // Refill.greedy保证首次创建令牌桶时是满的
        Bandwidth limitBandwidth = Bandwidth.classic(limit, Refill.greedy(limit, refillDuration));
        return Bucket4j.builder().addLimit(limitBandwidth).build();
    }
}
