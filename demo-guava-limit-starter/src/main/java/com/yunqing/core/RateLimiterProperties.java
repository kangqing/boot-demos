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
        private double defaultLimit;
        private Map<String, Double> specificIps;

        public double getDefaultLimit() {
            return defaultLimit;
        }

        public void setDefaultLimit(double defaultLimit) {
            this.defaultLimit = defaultLimit;
        }

        public Map<String, Double> getSpecificIps() {
            return specificIps;
        }

        public void setSpecificIps(Map<String, Double> specificIps) {
            this.specificIps = specificIps;
        }
    }

    public static class AppKeyLimits {
        private double defaultLimit;
        private Map<String, Double> specificAppkeys;

        public double getDefaultLimit() {
            return defaultLimit;
        }

        public void setDefaultLimit(double defaultLimit) {
            this.defaultLimit = defaultLimit;
        }

        public Map<String, Double> getSpecificAppkeys() {
            return specificAppkeys;
        }

        public void setSpecificAppkeys(Map<String, Double> specificAppkeys) {
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
