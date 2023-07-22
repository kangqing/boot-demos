package com.yunqing.service;

import com.yunqing.anno.RedissonLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author kangqing
 * @since 2023/7/22 08:02
 */
@Service
public class GoodsServiceImpl {

    @Resource
    private RedissonClient redissonClient;

    @RedissonLock(key = "#goodsId")
    public void lockTest1(Integer goodsId) {
        // 执行业务逻辑
        System.out.println("RPC调用-1，支付中心-支付");
        System.out.println("RPC调用-2，库存中心-扣减");
        System.out.println("RPC调用-3，账户中心-记账");
        System.out.println("RPC调用-4，等等其他-操作");
    }


    /**
     * 分布式锁一般使用方式
     */
    public void lockTest0(Integer goodsId) throws Exception {
        // 1.获取锁
        final RLock lock = redissonClient.getLock(String.valueOf(goodsId));
        final boolean tryLock = lock.tryLock(500, TimeUnit.MILLISECONDS);
        if (!tryLock) {
            throw new Exception("获取锁失败");
        }
        try {
            // 执行业务逻辑
            System.out.println("RPC调用-1，支付中心-支付");
            System.out.println("RPC调用-2，库存中心-扣减");
            System.out.println("RPC调用-3，账户中心-记账");
            System.out.println("RPC调用-4，等等其他-操作");
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
