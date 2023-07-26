package lock.service;

import cn.hutool.core.lang.Console;
import cn.hutool.system.SystemUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author kangqing
 * @since 2023/7/26 16:04
 */

@Service
public class RedissonLockService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private Redisson redisson;

    public void redissonLock() {
        String address = SystemUtil.getHostInfo().getAddress();
        System.out.println(address);
        RLock lock = redisson.getLock(address);
        try {
            lock.lock();
            // 票库存
            int stock = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("stock")));

            if (stock > 0) {
                stock -= 1;
                stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock));
                Console.log("购票成功，剩余票量 = {}", stock);
            } else {
                Console.log("库存不足");
            }
        } finally {
            lock.unlock();
        }
    }
}
