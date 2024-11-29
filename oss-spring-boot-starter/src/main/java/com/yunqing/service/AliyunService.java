package com.yunqing.service;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.Bucket;
import com.yunqing.config.OssConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/8/4 14:43
 */
@Component
@ConditionalOnProperty(name = "oss.type", havingValue = "ALIYUN")
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


    public void uploadFile(InputStream inputStream, String filePath, String fileName) {
        if (!StrUtil.isBlank(filePath)) {
            fileName = filePath + File.separator + fileName;
        }
        //调用方法实现上传
        ossClient.putObject(ossConfig.getBucket(), fileName, inputStream);
    }

    // 删除文件
    public boolean deleteFile(String filePath, String objectName) {
        if (!StrUtil.isBlank(filePath)) {
            objectName = filePath + File.separator + objectName;
        }
        ossClient.deleteObject(ossConfig.getBucket(), objectName);
        return true;
    }

    // 下载文件
    public InputStream downloadFile(String filePath, String objectName) {
        if (!StrUtil.isBlank(filePath)) {
            objectName = filePath + File.separator + objectName;
        }
        return ossClient.getObject(ossConfig.getBucket(), objectName)
                .getObjectContent();
    }

    /**
     * 检查文件是否存在
     *
     * @param fileName
     * @return
     */
    public boolean checkFileExist(String filePath, String fileName) {
        if (!StrUtil.isBlank(filePath)) {
            fileName = filePath + File.separator + fileName;
        }
        return ossClient.doesObjectExist(ossConfig.getBucket(), fileName);
    }

    public void batchUpload() {

    }

}
