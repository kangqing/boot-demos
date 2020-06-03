package com.yunqing.demoredis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yx
 * @description 参考 try.redis.io
 * @date 2020/6/3 16:08
 */
@SpringBootTest
@Slf4j
public class LettuceTest {

    RedisClient redisClient;
    StatefulRedisConnection<String, String> redisConnection;
    RedisCommands<String, String> sync;

    /**
     * @BeforeEach 注解在非静态方法上，所有测试方法之前执行
     * @BeforeAll 注解在静态方法上，所有测试方法之前执行
     */
    @BeforeEach
    void connRedisByLettuce() {
        redisClient = RedisClient.create("redis://192.168.16.128:6379");
        redisConnection = redisClient.connect();
        sync = redisConnection.sync();
    }

    /**
     * 关闭连接
     */
    @AfterEach
    void closeConn() {
        redisConnection.close();
        redisClient.shutdown();
    }

    /**
     * string类型set get
     * ttl 返回-1永不超时
     * ttl 返回-2不存在此key
     */
    @Test
    void string() {
        log.info(sync.set("k1", "v1"));
        sync.expire("k1", 1000); //设置1000秒超时
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(sync.ttl("k1").toString()); //获取剩余超时时间
        log.info(sync.get("k1"));
    }

    /**
     * redis 列表，相当于链表
     * lpush 左侧添加，是可变参数，可同时追加多个
     * rpush 右侧添加，是可变参数，可同时追加多个
     * lrange key 0 -1 获取列表第一到最后一个
     * lpop key 删除列表左侧第一个
     * rpop key 删除列表右侧第一个
     * llen key 获取当前列表长度
     */
    @Test
    void redisList() {
        sync.rpush("friends", "Tom", "yunqing");
        sync.rpush("friends", "Bob", "kls");
        sync.lpush("friends", "peter");
        log.info(sync.llen("friends").toString());
        List<String> friends = sync.lrange("friends", 0, -1);
        friends.forEach(System.out::println);
        sync.lpop("friends");
        sync.rpop("friends");
        List<String> friends2 = sync.lrange("friends", 0, -1);
        friends2.forEach(System.out::println);
        log.info(sync.llen("friends").toString());
        sync.expire("friends", 10);//设置1000秒过期
    }

    /**
     * redis  set集合
     */
    @Test
    void redisSet() {
        sync.sadd("names", "yunqing", "kls", "tom", "tom");
        log.info("输出set集合[{}]", String.valueOf(sync.smembers("names")));
        log.info("测试是否存在---------[{}]", sync.sismember("names", "yunqing"));
        log.info("删除结果1成功，0失败---------[{}}]", sync.srem("names", "tom"));
        log.info("输出集合[{}]", sync.smembers("names"));
        sync.expire("names", 10);
    }

}
