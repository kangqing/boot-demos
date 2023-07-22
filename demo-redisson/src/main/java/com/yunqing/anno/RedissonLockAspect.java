package com.yunqing.anno;

import com.yunqing.service.LockService;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * ******************************
 *  轮子见 Lock4j 框架
 * ******************************
 * @author kangqing
 * @since 2023/7/22 14:09
 */
@Aspect
@Component
@Order(0) // 确保此事务锁先执行，分布式锁在事务外
public class RedissonLockAspect {

    @Resource
    private LockService lockService;

    @Around("@annotation(com.yunqing.anno.RedissonLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);
        // 默认方法限定名 + 注解排名（可能多个）
        String prefix = StringUtil.isBlank(redissonLock.prefixKey()) ? SpElUtils.getMethodKey(method)
                : redissonLock.prefixKey();
        String key = SpElUtils.parseSpEl(method, joinPoint.getArgs(), redissonLock.key());
        return lockService.executeWithLockThrows(prefix + ":" + key, redissonLock.waitTime(), redissonLock.unit(),
                joinPoint::proceed);
    }
}
