package com.yunqing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * @author kangqing
 * @since 2024/8/26 16:11
 */
@Slf4j
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationHandle {

    /**
     * 告警通道
     * @return
     */
    @Bean
    public MessageChannel alarmEventChannel() {
        return new DirectChannel();
    }

    @Bean
    @SuppressWarnings(value = "unchecked")
    public IntegrationFlow alarmEventFlow() {
        return IntegrationFlows.from(alarmEventChannel())
                .aggregate(a -> a
                        .releaseStrategy(group -> {
                            boolean release = group.size() >= 1000 || group.getMessages().stream()
                                    .anyMatch(m -> Duration.between((Instant) Objects.requireNonNull(m.getHeaders().get("time")),
                                            Instant.now()).toMillis() > 5000);
                            if (!release && log.isDebugEnabled()) {
                                log.info("alarm messages size: {}", group.getMessages().size());
                            }
                            return release;
                        })
                        .correlationStrategy(m -> 1)
                        .expireGroupsUponCompletion(true) // 确保组在完成后过期
                        .groupTimeout(10000)) // 10秒后，达不到条件释放组，不处理
                .handle((payload, headers) -> {
                    List<AlarmInfo> list = (List<AlarmInfo>) payload;
                    if (log.isDebugEnabled()) {
                        list.forEach(System.out::println);
                    }
                    //alarmDBDao.batchSave(list);
                    return null;
                })
                .get();
    }

}
