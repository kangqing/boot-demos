package com.yunqing.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 限流配置文件
 * @author kangqing
 * @since 2024/9/13 10:07
 */
@Component
@ConfigurationProperties(prefix = "rate-limiter")
public class RateLimiterProperties {

    private IpLimits ipLimits;
    private AppKeyLimits appkeyLimits;


    public static class IpLimits {
        private long defaultLimit;
        private Map<String, Long> specificIps;

        public long getDefaultLimit() {
            return defaultLimit;
        }

        public void setDefaultLimit(long defaultLimit) {
            this.defaultLimit = defaultLimit;
        }

        public Map<String, Long> getSpecificIps() {
            return specificIps;
        }

        public void setSpecificIps(Map<String, Long> specificIps) {
            this.specificIps = specificIps;
        }
    }

    public static class AppKeyLimits {
        private long defaultLimit;
        private Map<String, Long> specificAppkeys;

        public long getDefaultLimit() {
            return defaultLimit;
        }

        public void setDefaultLimit(long defaultLimit) {
            this.defaultLimit = defaultLimit;
        }

        public Map<String, Long> getSpecificAppkeys() {
            return specificAppkeys;
        }

        public void setSpecificAppkeys(Map<String, Long> specificAppkeys) {
            this.specificAppkeys = specificAppkeys;
        }
    }

    public IpLimits getIpLimits() {
        return ipLimits;
    }

    public void setIpLimits(IpLimits ipLimits) {
        this.ipLimits = ipLimits;
    }

    public AppKeyLimits getAppkeyLimits() {
        return appkeyLimits;
    }

    public void setAppkeyLimits(AppKeyLimits appkeyLimits) {
        this.appkeyLimits = appkeyLimits;
    }
}
