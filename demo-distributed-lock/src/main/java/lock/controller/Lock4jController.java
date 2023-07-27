package lock.controller;

import cn.hutool.core.lang.Console;
import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import com.baomidou.lock.annotation.Lock4j;
import com.baomidou.lock.exception.LockException;
import lock.conf4j.RedissonLockNewExecutor;
import lock.service.LocalLockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author kangqing
 * @since 2023/7/27 16:21
 */
@RestController
@RequiredArgsConstructor
public class Lock4jController {

    private final StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String port;

    @Resource
    private LocalLockService lockService;

    /**
     * Lock4j 分布式锁解决方案
     * 推荐，代码侵入性低
     * 配置锁超时时间，锁过期时间，防止死锁
     */
    @GetMapping("/lock4j")
    private String sellTicket() {
        // 票库存
        lockService.localLock("Lock4j");
        return "success";
    }


    /**
     * API编程式方案
     * 具有一定代码侵入性，更加灵活
     * @return
     */
//    @Resource
//    private LockTemplate lockTemplate;
//
//    @GetMapping("/lock4j")
//    private String sellTicket1() {
//        final LockInfo lock = lockTemplate.lock("lock4j", 10000, 20000, RedissonLockNewExecutor.class);
//        if (lock == null) {
//            // 申请锁失败
//            throw new LockException("业务处理中，请稍后再试...");
//        }
//        try {
//            // 票库存
//            int stock = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("stock")));
//
//            if (stock > 0) {
//                stock -= 1;
//                stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock));
//                Console.log("访问端口 = {}, 购票成功，剩余票量 = {}", port, stock);
//            } else {
//                Console.log("库存不足, 访问端口 = {}", port);
//            }
//        } finally {
//            lockTemplate.releaseLock(lock);
//        }
//        return "success";
//    }


}
