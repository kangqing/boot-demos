package lock.conf4j;

import com.baomidou.lock.executor.AbstractLockExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 重入锁
 * @author kangqing
 * @since 2023/7/27 16:16
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedissonLockNewExecutor extends AbstractLockExecutor<RLock> {

    private final RedissonClient redissonClient;

    @Override
    public boolean renewal() {
        return true;
    }

    @Override
    public RLock acquire(String lockKey, String lockValue, long expire, long acquireTimeout) {
        try {
            final RLock lockInstance = redissonClient.getLock(lockKey);
            final boolean locked = lockInstance.tryLock(acquireTimeout, expire, TimeUnit.MILLISECONDS);
            return obtainLockInstance(locked, lockInstance);
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public boolean releaseLock(String key, String value, RLock lockInstance) {
        if (lockInstance.isHeldByCurrentThread()) {
            try {
                return lockInstance.forceUnlockAsync().get();
            } catch (ExecutionException | InterruptedException e) {
                return false;
            }
        }
        return false;
    }

}