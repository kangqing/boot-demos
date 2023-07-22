package com.yunqing.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author kangqing
 * @since 2023/7/22 13:57
 */
@Service
public class LockService {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 通过调用此方法用分布式锁，这样有代码侵入性
     * 可以利用注解和AOP解决
     * @param key
     * @param waitTime
     * @param unit
     * @param supplier
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T executeWithLockThrows(String key, int waitTime, TimeUnit unit, SupplierThrow<T> supplier)
            throws Throwable {
        RLock lock = redissonClient.getLock(key);
        boolean lockSuccess = lock.tryLock(waitTime, unit);
        if (!lockSuccess) {
            throw new Exception("获取分布式锁失败");
        }
        try {
            // 执行锁内代码逻辑
            return supplier.get();
        } finally {
            // 是否已经加锁，并且当前线程保持锁定
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }

    @FunctionalInterface
    public interface SupplierThrow<T> {
        T get() throws Throwable;
    }
}
