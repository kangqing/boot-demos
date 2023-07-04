package com.yunqing;

import com.yunqing.batch.consumer.Demo05Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/2 20:07
 */
@Slf4j
@SpringBootTest
public class Demo05ProducerTest {

    @Resource
    private Demo05Producer producer;

    @Test
    public void testSyncSend01() throws InterruptedException {
        // 发送 3 条消息
        this.testSyncSend(3);
    }

    @Test
    public void testSyncSen02() throws InterruptedException {
        // 发送 10 条消息
        this.testSyncSend(10);
    }

    private void testSyncSend(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // 同步发送消息
            int id = (int) (System.currentTimeMillis() / 1000);
            producer.syncSend(id, "批量消费");
            log.info("[testSyncSendMore][发送编号：[{}] 发送成功]", id);
        }
    }

}
