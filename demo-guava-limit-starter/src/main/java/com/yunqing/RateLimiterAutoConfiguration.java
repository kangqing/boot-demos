package com.yunqing;

import com.yunqing.core.RateLimiterProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 限流自动配置类，会在spring初始化时自动配置
 * @author kangqing
 * @since 2024/9/13 09:45
 */
@Configuration
@ComponentScan(basePackages = "com.yunqing")
@EnableConfigurationProperties(RateLimiterProperties.class)
public class RateLimiterAutoConfiguration {

}
