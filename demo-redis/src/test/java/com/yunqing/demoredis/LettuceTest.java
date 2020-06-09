package com.yunqing.demoredis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * Lettuce主要提供三种API：
     * 同步（sync）：RedisCommands。
     * 异步（async）：RedisAsyncCommands。
     * 反应式（reactive）：RedisReactiveCommands。
     */
    @BeforeEach
    void connRedisByLettuce() {
        //创建单机连接的连接命令
        RedisURI redisUri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))//连接超时时间
                .build();
        //创建客户端
        redisClient = RedisClient.create(redisUri);
        //创建线程安全的连接
        redisConnection = redisClient.connect();
        //创建同步命令
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
        sync.rpush("friends", "Bob", "kkk");
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
     * 测试
     */
    @Test
    void redisSet() {
        sync.sadd("names", "yunqing", "kkk", "tom", "tom", "a", "c", "b");
        log.info("输出set集合[{}]", String.valueOf(sync.smembers("names")));
        log.info("测试是否存在---------[{}]", sync.sismember("names", "yunqing"));
        log.info("删除结果1成功，0失败---------[{}}]", sync.srem("names", "tom"));
        log.info("输出集合[{}]", sync.smembers("names"));
        sync.expire("names", 10);
    }

    /**
     * 带排序的set集合
     */
    @Test
    void redisZSet() {
        sync.zadd("names", 1, "yunqing");
        sync.zadd("names", 3, "tom");
        sync.zadd("names", 2, "kkk");
        sync.zadd("names", 10, "tom");
        sync.zadd("names", 5, "peter");
        sync.zadd("names", 5, "bob");
        log.info("查看集合所有[{}]---------", sync.zrange("names", 0, -1));
        log.info("删除一个-----[{}]", sync.zrem("names", "peter"));
        log.info("查看集合所有[{}]---------", sync.zrange("names", 0, -1));
        sync.expire("names", 10);
    }

    /**
     * hash格式存储
     */
    @Test
    void hash() {
        sync.hset("user", "name", "yunqing");
        sync.hset("user", "age", "26");
        log.info("获取所有-----[{}]", sync.hgetall("user"));
        Map<String, String> map = new HashMap<>();
        map.put("sex", "男");
        map.put("email", "10001");
        sync.hmset("user", map);
        log.info("获取所有-----[{}]", sync.hgetall("user"));
        log.info("获取一个------[{}]", sync.hget("user", "email"));
        sync.expire("user", 10);
    }

    /**
     * Lettuce的管道不是真正的管道，效率没有 Jedis 的高 ？？？有待考证
     */

}
