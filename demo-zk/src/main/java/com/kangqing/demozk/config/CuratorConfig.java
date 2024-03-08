package com.kangqing.demozk.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/9/1 14:56
 */
@Configuration
public class CuratorConfig {

    @Resource
    private WrapperZK wrapperZK;

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.newClient(
                wrapperZK.getConnectString(),
                wrapperZK.getSessionTimeoutMs(),
                wrapperZK.getConnectionTimeoutMs(),
                new RetryNTimes(wrapperZK.getRetryCount(), wrapperZK.getElapsedTimeMs())
        );
    }
}
