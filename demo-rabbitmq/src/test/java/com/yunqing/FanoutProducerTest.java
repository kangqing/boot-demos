package com.yunqing;

import com.yunqing.fanout.FanoutProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/1 14:10
 */
@Slf4j
@SpringBootTest
public class FanoutProducerTest {

    @Resource
    private FanoutProducer producer;

    @Test
    public void test() {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, "广播消息");
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
    }
}
