package lock;

import cn.hutool.core.lang.Console;
import cn.hutool.system.SystemUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Redisson 分布式锁
 * @author kangqing
 * @since 2021/12/14 20:44
 */
@RestController
@RequiredArgsConstructor
public class RedissonLockController {

    private final StringRedisTemplate stringRedisTemplate;

    private final Redisson redisson;

    /**
     * redisson 底层使用 Lua 脚本保证原子性
     * 改造使用 redisson 分布式锁解决了超卖现象
     * @return
     */
    @GetMapping("/redisson")
    private synchronized String sellTicket() {
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

        return "success";
    }
}
