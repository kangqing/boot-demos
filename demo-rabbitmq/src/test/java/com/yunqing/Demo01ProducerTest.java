package com.yunqing;

import com.yunqing.direct.Demo01Producer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/6/30 15:04
 */
@SpringBootTest
public class Demo01ProducerTest {
    @Resource
    private Demo01Producer producer;

    @Test
    void test01(){
        int id = (int) System.currentTimeMillis() / 1000;
        producer.syncSend(id, "测试01");
    }

    @Test
    void test02(){
        int id = (int) System.currentTimeMillis() / 1000;
        producer.syncSendDefault(id, "测试02");
    }

    @Test
    void test03() throws Exception {
        int id = (int) System.currentTimeMillis() / 1000;
        producer.asyncSendMessage(id, "测试03异步");
    }
}
