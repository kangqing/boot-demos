package com.yunqing.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.yunqing.config.OssConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/8/4 14:43
 */
@Component
public class AliyunService {

    private final OssConfig ossConfig;

    public AliyunService(OssConfig ossConfig) {
        this.ossConfig = ossConfig;
    }

    @Resource
    private OSS ossClient;


    // 检查桶是否存在
    public boolean checkBucketExist(String bucketName) {
        return ossClient.doesBucketExist(bucketName);
    }

    // 创建桶
    public boolean createBucket(String bucketName) {
        final boolean b = checkBucketExist(bucketName);
        if (!b) {
            ossClient.createBucket(bucketName);
        }
        return true;
    }

    // 列出所有桶
    public List<Bucket> listBuckets() {
        return ossClient.listBuckets();
    }


    public void uploadFile(InputStream inputStream, String fileName) {
        //调用方法实现上传
        ossClient.putObject(ossConfig.getBucket(), fileName, inputStream);
    }

    // 删除文件
    public boolean deleteFile(String objectName) {
        ossClient.deleteObject(ossConfig.getBucket(), objectName);
        return true;
    }

    // 下载文件
    public InputStream downloadFile(String objectName) {
        return ossClient.getObject(ossConfig.getBucket(), objectName)
                .getObjectContent();
    }

}
