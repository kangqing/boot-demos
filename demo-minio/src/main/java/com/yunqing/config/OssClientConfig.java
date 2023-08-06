package com.yunqing.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/8/5 13:27
 */
@Configuration
public class OssClientConfig {

    @Resource
    private OssConfig ossConfig;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(ossConfig.getEndpoint())
                .credentials(ossConfig.getAccessKey(), ossConfig.getSecretKey())
                .region(ossConfig.getRegion())
                .build();
    }

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder()
                .build(ossConfig.getEndpoint(), ossConfig.getAccessKey(), ossConfig.getSecretKey());
    }
}
