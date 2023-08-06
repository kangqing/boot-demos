package com.yunqing.service;

import com.yunqing.config.OssConfig;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/8/4 14:26
 */
@Component
public class MinioService {

    private final OssConfig ossConfig;

    @Resource
    private MinioClient minioClient;


    public MinioService(OssConfig ossConfig) {
        this.ossConfig = ossConfig;
    }

    // 检查桶是否存在
    public boolean checkBucketExist(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .region(ossConfig.getRegion())
                .build());
    }

    // 创建桶
    public boolean createBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean isExist = checkBucketExist(bucketName);
        if (!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .region(ossConfig.getRegion())
                    .build());
        }
        return true;
    }

    // 列出所有桶
    public List<Bucket> listBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.listBuckets();
    }

    // 上传文件
    public void uploadFile(InputStream stream, String objectName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(ossConfig.getBucket())
                .region(ossConfig.getRegion())
                .object(objectName)
                .stream(stream, stream.available(), -1)
                .build());
    }

    // 删除文件
    public boolean deleteFile(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(ossConfig.getBucket())
                .region(ossConfig.getRegion())
                .object(objectName)
                .build());
        return true;
    }

    // 下载文件
    public InputStream downloadFile(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(ossConfig.getBucket())
                .region(ossConfig.getRegion())
                .object(objectName)
                .build());
    }
}
