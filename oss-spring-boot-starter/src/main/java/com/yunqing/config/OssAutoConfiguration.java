package com.yunqing.config;

import com.yunqing.api.OssOperateRpcApi;
import com.yunqing.service.OssRpcFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * @author kangqing
 * @since 2023/8/4 10:11
 */
@Configuration
// 导入我们自定义的配置类,供当前类使用
@EnableConfigurationProperties(value = OssConfig.class)
// 只有web应用程序时此自动配置类才会生效
@ConditionalOnWebApplication
//判断oss.enabled 的值是否为“true”， matchIfMissing = true：没有该配置属性时也会正常加载
@ConditionalOnProperty(prefix = "oss", name = "enabled", havingValue = "true")
public class OssAutoConfiguration {

    @Resource
    private OssConfig ossConfig;

    @Bean
    public OssOperateRpcApi ossOperateRpcApi() {
        return OssRpcFactory.createOssOperateRpcApi(this.ossConfig);
    }
}
