package com.yunqing.config;

import com.yunqing.service.OssRpcFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author kangqing
 * @since 2023/8/4 10:08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oss")
public class OssConfig {

    private boolean enabled = false;

    private OssRpcFactory.OssType type;

    private String accessKey;

    private String secretKey;
    /**
     * 桶名称
     */
    private String bucket;
    /**
     * url
     */
    private String endpoint;

    /**
     * minio region config
     */
    private String region;
}
