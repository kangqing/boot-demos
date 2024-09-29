package com.yunqing.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 从配置文件中获取限流数量配置
 * @author kangqing
 * @since 2024/9/12 19:22
 */
@Service
public class RateLimiterConfigService {

    // 限流配置文件
    private final RateLimiterProperties rateLimiterProperties;

    @Autowired
    public RateLimiterConfigService(RateLimiterProperties rateLimiterProperties) {
        this.rateLimiterProperties = rateLimiterProperties;
    }

    // 根据 IP 获取限流值，如果 IP 没有配置则使用默认值
    public double getRateLimitByIp(String ip) {
        // 从 RateLimiterProperties 获取 IP 限流配置
        return rateLimiterProperties.getIpLimits().getSpecificIps()
                .getOrDefault(ip, (double) rateLimiterProperties.getIpLimits().getDefaultLimit());
    }

    // 根据 AppKey 获取限流值，如果 AppKey 没有配置则使用默认值
    public double getRateLimitByAppKey(String appKey) {
        // 从 RateLimiterProperties 获取 AppKey 限流配置
        return rateLimiterProperties.getAppkeyLimits().getSpecificAppkeys()
                .getOrDefault(appKey, (double) rateLimiterProperties.getAppkeyLimits().getDefaultLimit());
    }
}
