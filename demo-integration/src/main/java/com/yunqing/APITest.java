package com.yunqing;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;

/**
 * @author kangqing
 * @since 2024/8/26 16:14
 */
@Service
public class APITest {

    @Resource
    private MessageChannel alarmEventChannel;

    public void test() {
        // Send to alarmEventChannel
        alarmEventChannel.send(MessageBuilder.withPayload(new AlarmInfo())
                .setHeader("time", Instant.now())
                .build());
    }
}
