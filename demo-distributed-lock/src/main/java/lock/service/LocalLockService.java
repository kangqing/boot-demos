package lock.service;

import cn.hutool.core.lang.Console;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author kangqing
 * @since 2023/7/26 15:47
 */
@Service
public class LocalLockService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void localLock() {
        int stock = 0;
        try {
            final String stock1 = stringRedisTemplate.opsForValue().get("stock");
            stock = Integer.parseInt(Objects.requireNonNull(stock1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (stock > 0) {
            stock -= 1;
            stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock));
            Console.log("购票成功，剩余票量 = {}", stock);
        } else {
            Console.log("库存不足");
        }
    }
}
