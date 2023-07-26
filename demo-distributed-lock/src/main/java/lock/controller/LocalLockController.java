package lock.controller;

import cn.hutool.core.lang.Console;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 本地锁，依然会出现超卖现象
 * @author kangqing
 * @since 2021/12/14 18:53
 */
@RestController
@RequiredArgsConstructor
public class LocalLockController {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 本地同步代码块，本地锁，分布式环境下会出现问题
     * 例如使用 nginx 负载均衡均摊请求到两台服务器上，8081端口和9090端口各启动一个服务
     * 使用 nginx 负载到两台服务器上
     * 使用 jmeter 每秒发送 300 个请求，循环3次进行买票
     * 就可以看到超卖现象了
     */
    @GetMapping("/sell")
    private synchronized String sellTicket() {
        // 票库存
        int stock = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("stock")));

        if (stock > 0) {
            stock -= 1;
            stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock));
            Console.log("购票成功，剩余票量 = {}", stock);
        } else {
            Console.log("库存不足");
        }
        return "success";
    }
}
