package com.yunqing.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.yunqing.config.OssConfig;
import com.yunqing.event.OssBatchUploadEvent;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Component
@ConditionalOnProperty(name = "oss.type", havingValue = "AMAZON_S3")
public class AmazonS3Service {

    private final ApplicationEventPublisher eventPublisher;

    @Resource
    private OssConfig ossConfig;

    @Resource
    private S3Client s3Client;

    public AmazonS3Service(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

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
