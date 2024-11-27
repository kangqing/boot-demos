package com.yunqing.service;

import cn.hutool.core.io.IoUtil;
import com.yunqing.api.OssOperateRpcApi;
import com.yunqing.dto.OssProcessDTO;
import com.yunqing.dto.RpcRequest;
import com.yunqing.dto.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class AmazonS3RpcOssService implements OssOperateRpcApi {

    @Resource
    private AmazonS3Service amazonS3Service;

    @Override
    public RpcResponse<?> create(RpcRequest<String> rpcRequest) {
        try {
            return amazonS3Service.createBucket(rpcRequest.getEntity()) ?
                    RpcResponse.success() : RpcResponse.failure("创建 S3 bucket 失败！");
        } catch (Exception e) {
            log.error("创建 S3 bucket 失败！", e);
            return RpcResponse.failure("创建 S3 bucket 失败！");
        }
    }

    @Override
    public RpcResponse<?> upload(RpcRequest<OssProcessDTO> rpcRequest) {
        try (InputStream inputStream = new FileInputStream(rpcRequest.getEntity().getFilePath())) {
            amazonS3Service.uploadFile(inputStream, rpcRequest.getEntity().getFileName());
            return RpcResponse.success();
        } catch (Exception e) {
            log.error("上传文件失败！", e);
            return RpcResponse.failure("上传文件失败！");
        }
    }

    @Override
    public RpcResponse<?> remove(RpcRequest<OssProcessDTO> rpcRequest) {
        try {
            return amazonS3Service.deleteFile(rpcRequest.getEntity().getFileName()) ?
                    RpcResponse.success() : RpcResponse.failure("删除文件失败！");
        } catch (Exception e) {
            log.error("删除文件失败！", e);
            return RpcResponse.failure("删除文件失败！");
        }
    }

    @Override
    public RpcResponse<?> download(RpcRequest<OssProcessDTO> rpcRequest) {
        try (InputStream inputStream = amazonS3Service.downloadFile(rpcRequest.getEntity().getFileName());
             OutputStream outputStream = new FileOutputStream(rpcRequest.getEntity().getFilePath())) {
            IoUtil.copy(inputStream, outputStream);
            return RpcResponse.success(rpcRequest.getEntity().getFilePath());
        } catch (Exception e) {
            log.error("下载文件失败！", e);
            return RpcResponse.failure("下载文件失败！");
        }
    }

    /**
     * 检查文件存在
     * @param rpcRequest
     * @return
     */
    @Override
    public RpcResponse<?> checkFileExist(RpcRequest<OssProcessDTO> rpcRequest) {
        boolean b = amazonS3Service.checkFileExist(rpcRequest.getEntity().getFileName());
        return RpcResponse.success(b);
    }


}
