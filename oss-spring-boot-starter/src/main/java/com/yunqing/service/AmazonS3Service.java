package com.yunqing.service;

import cn.hutool.core.util.StrUtil;
import com.yunqing.config.OssConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Component
@ConditionalOnProperty(name = "oss.type", havingValue = "AMAZON_S3")
public class AmazonS3Service {

    @Resource
    private OssConfig ossConfig;

    @Resource
    private S3Client s3Client;

    // 检查桶是否存在
    public boolean checkBucketExist(String bucketName) {
        try {
            s3Client.headBucket(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
            return true;
        } catch (S3Exception e) {
            return false;
        }
    }

    // 创建桶
    public boolean createBucket(String bucketName) {
        if (!checkBucketExist(bucketName)) {
            s3Client.createBucket(CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(CreateBucketConfiguration.builder()
                            .locationConstraint(ossConfig.getRegion())
                            .build())
                    .build());
        }
        return true;
    }

    // 列出所有桶
    public List<Bucket> listBuckets() {
        return s3Client.listBuckets().buckets();
    }

    // 上传文件
    public void uploadFile(InputStream stream, String filePath, String objectName) throws IOException {
        // 创建临时文件
        File tempFile = File.createTempFile("upload-", ".tmp");

        try (OutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] temp = new byte[8192];
            int bytesRead;
            while ((bytesRead = stream.read(temp)) != -1) {
                outputStream.write(temp, 0, bytesRead);
            }
        }
        if (!StrUtil.isBlank(filePath)) {
            objectName = filePath + File.separator + objectName;
        }
        // 获取文件长度并上传
        try (InputStream fileStream = new FileInputStream(tempFile)) {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(ossConfig.getBucket())
                            .key(objectName)
                            .build(),
                    RequestBody.fromInputStream(fileStream, tempFile.length()));
        } finally {
            // 删除临时文件
            tempFile.delete();
        }

    }

    // 删除文件
    public boolean deleteFile(String filePath, String objectName) {
        if (!StrUtil.isBlank(filePath)) {
            objectName = filePath + File.separator + objectName;
        }
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(ossConfig.getBucket())
                .key(objectName)
                .build());
        return true;
    }

    // 下载文件
    public InputStream downloadFile(String filePath, String objectName) {
        if (!StrUtil.isBlank(filePath)) {
            objectName = filePath + File.separator + objectName;
        }
        return s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(ossConfig.getBucket())
                        .key(objectName)
                        .build()
        );
    }


    /**
     * 检查文件是否存在
     * @param fileName
     * @return
     */
    public boolean checkFileExist(String filePath, String fileName) {
        if (!StrUtil.isBlank(filePath)) {
            fileName = filePath + File.separator + fileName;
        }
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(ossConfig.getBucket())
                    .key(fileName)
                    .build();
            HeadObjectResponse response = s3Client.headObject(headObjectRequest);
            return response != null; // 如果返回不为 null，则文件存在
        } catch (S3Exception e) {
            if (e.statusCode() == 404) { // 对象不存在
                return false;
            }
            throw new RuntimeException("检查文件是否存在时发生错误", e); // 其他错误
        }
    }
}
