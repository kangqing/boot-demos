package com.kangqing.demozk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kangqing
 * @since 2023/9/1 14:53
 */
@Data
@Component
@ConfigurationProperties(prefix = "curator")
public class WrapperZK {

    private int retryCount;

    private int elapsedTimeMs;

    private String connectString;

    private int sessionTimeoutMs;

    private int connectionTimeoutMs;
}
