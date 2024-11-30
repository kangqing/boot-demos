package com.yunqing.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.Bucket;
import com.yunqing.config.OssConfig;
import com.yunqing.event.OssBatchUploadEvent;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/8/4 14:43
 */
@Component
@ConditionalOnProperty(name = "oss.type", havingValue = "ALIYUN")
public class AliyunService {

    private final OssConfig ossConfig;

    private final ApplicationEventPublisher eventPublisher;

    public AliyunService(OssConfig ossConfig, ApplicationEventPublisher eventPublisher) {
        this.ossConfig = ossConfig;
        this.eventPublisher = eventPublisher;
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

    /**
     * 批量上传压缩包里的文件
     * @param zipInputStream 压缩包流
     * @param targetPath OSS 目标路径
     * @throws Exception 解压或上传失败
     */
    public void batchUploadZipFile(InputStream zipInputStream, String targetPath) throws Exception {
        // 创建临时目录存放解压文件
        File tempDir = new File(System.getProperty("java.io.tmpdir") + File.separator + "oss_temp");
        if (!tempDir.exists() && !tempDir.mkdirs()) {
            throw new RuntimeException("临时目录创建失败！");
        }

        // 将压缩包保存到临时目录
        File zipFile = new File(tempDir, "temp.zip");
        FileUtil.writeFromStream(zipInputStream, zipFile);

        List<String> uploadedFiles = new ArrayList<>();

        // 解压缩包
        try (ZipFile zip = new ZipFile(zipFile)) {
            Enumeration<ZipArchiveEntry> entries = zip.getEntries();
            while (entries.hasMoreElements()) {
                ZipArchiveEntry entry = entries.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                File extractedFile = new File(tempDir, entry.getName());
                FileUtil.writeFromStream(zip.getInputStream(entry), extractedFile);

                // 上传解压后的文件
                try (InputStream fileStream = FileUtil.getInputStream(extractedFile)) {
                    uploadFile(fileStream, targetPath, entry.getName());
                    uploadedFiles.add(entry.getName()); // 记录上传成功的文件名
                }
            }
        } finally {
            // 清理临时目录
            FileUtil.del(tempDir);
        }

        // 发布事件，通知上传成功的文件列表
        eventPublisher.publishEvent(new OssBatchUploadEvent(this, uploadedFiles));
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
