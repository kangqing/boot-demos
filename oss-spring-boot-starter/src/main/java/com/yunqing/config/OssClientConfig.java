package com.yunqing.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @author kangqing
 * @since 2023/8/5 13:27
 */
@Configuration
public class OssClientConfig {

    @Resource
    private OssConfig ossConfig;

    /**
     * MINIO OSS
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "oss.type", havingValue = "MINIO")
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(ossConfig.getEndpoint())
                .credentials(ossConfig.getAccessKey(), ossConfig.getSecretKey())
                .region(ossConfig.getRegion())
                .build();
    }

    /**
     * 阿里云 OSS
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "oss.type", havingValue = "ALIYUN")
    public OSS ossClient() {
        return new OSSClientBuilder()
                .build(ossConfig.getEndpoint(), ossConfig.getAccessKey(), ossConfig.getSecretKey());
    }

    /**
     * 亚马逊 OSS
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "oss.type", havingValue = "AMAZON_S3")
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create(ossConfig.getEndpoint()))
                .region(Region.of(ossConfig.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
                        ossConfig.getAccessKey(), ossConfig.getSecretKey())))
                .build();
    }


}
