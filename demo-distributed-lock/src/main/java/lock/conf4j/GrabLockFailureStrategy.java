package lock.conf4j;

import com.baomidou.lock.LockFailureStrategy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义抢占锁失败执行策略
 * @author kangqing
 * @since 2023/7/27 16:15
 */
@Component
public class GrabLockFailureStrategy implements LockFailureStrategy {

    @Override
    public void onLockFailure(String key, Method method, Object[] arguments) {
        throw new RuntimeException("获取锁失败啦");
    }
}