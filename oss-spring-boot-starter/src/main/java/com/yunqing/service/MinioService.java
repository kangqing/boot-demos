package com.yunqing.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.yunqing.config.OssConfig;
import com.yunqing.event.OssBatchUploadEvent;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.Setter;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/8/4 14:26
 */
@Component
@ConditionalOnProperty(name = "oss.type", havingValue = "MINIO")
public class MinioService {

    private final ApplicationEventPublisher eventPublisher;

    private final OssConfig ossConfig;

    @Resource
    private MinioClient minioClient;


    public MinioService(ApplicationEventPublisher eventPublisher, OssConfig ossConfig) {
        this.eventPublisher = eventPublisher;
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

    // 上传文件
    public void uploadFile(InputStream stream, String filePath, String objectName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!StrUtil.isBlank(filePath)) {
            objectName = filePath + File.separator + objectName;
        }
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(ossConfig.getBucket())
                .region(ossConfig.getRegion())
                .object(objectName)
                .stream(stream, stream.available(), -1)
                .build());
    }

    // 删除文件
    public boolean deleteFile(String filePath, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!StrUtil.isBlank(filePath)) {
            objectName = filePath + File.separator + objectName;
        }
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(ossConfig.getBucket())
                .region(ossConfig.getRegion())
                .object(objectName)
                .build());
        return true;
    }

    // 下载文件
    public InputStream downloadFile(String filePath, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!StrUtil.isBlank(filePath)) {
            objectName = filePath + File.separator + objectName;
        }
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(ossConfig.getBucket())
                .region(ossConfig.getRegion())
                .object(objectName)
                .build());
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
        try {
            return minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(ossConfig.getBucket())
                            .object(fileName)
                            .build()
            ) != null;
        } catch (MinioException e) {
            // 如果是不存在的文件，MinioException通常会抛出NoSuchKey异常
            if (e.getMessage().contains("Not Found") || e.getMessage().contains("NoSuchKey")) {
                return false;
            }
            throw new RuntimeException("检查文件是否存在时发生错误", e);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
