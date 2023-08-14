package com.kangqing.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author kangqing
 * @since 2023/8/14 15:48
 */
@Data
@Component
public class XxlAutoRegisterProperties {
    @Value("${xxl.job.admin.addresses}")
    private String url;
    @Value("${xxl.job.admin.username}")
    private String username;
    @Value("${xxl.job.admin.password}")
    private String password;
    // 执行器appName
    @Value("${xxl.job.executor.appName}")
    private String appName;
    // 执行器名称
    @Value("${xxl.job.executor.title}")
    private String title;
}
