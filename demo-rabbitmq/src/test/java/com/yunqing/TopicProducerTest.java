package com.yunqing;

import com.yunqing.topic.Demo02Message;
import com.yunqing.topic.TopicProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/1 13:46
 */
@SpringBootTest
public class TopicProducerTest {

    @Resource
    private TopicProducer topicProducer;

    @Test
    public void testSuccess() {
        int id = (int) System.currentTimeMillis() / 1000;
        topicProducer.syncSend(id, "匹配成功", "hello.kang.qing");
    }

    @Test
    public void testFailure() {
        int id = (int) System.currentTimeMillis() / 1000;
        topicProducer.syncSend(id, "匹配失败", "111.hello.kang.qing");
    }
}
