package com.yunqing.service;

import cn.hutool.core.io.IoUtil;
import com.yunqing.api.OssOperateRpcApi;
import com.yunqing.dto.OssProcessDTO;
import com.yunqing.dto.RpcRequest;
import com.yunqing.dto.RpcResponse;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author kangqing
 * @since 2023/8/4 08:45
 */
@Slf4j
public class MinioRpcOssService implements OssOperateRpcApi {

    @Resource
    private MinioService minioService;

    @Override
    public RpcResponse<?> create(RpcRequest<String> rpcRequest) {
        try {
            return minioService.createBucket(rpcRequest.getEntity()) ? RpcResponse.success():
                    RpcResponse.failure("创建oss bucket失败！");
        } catch (ServerException | ErrorResponseException | IOException | NoSuchAlgorithmException | InsufficientDataException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            throw new RuntimeException("创建oss bucket失败！");
        }
    }

    @Override
    public RpcResponse<?> upload(RpcRequest<OssProcessDTO> rpcRequest) {
        try (InputStream inputStream =
                     IoUtil.toStream(new File(rpcRequest.getEntity().getFilePath()));) {
            minioService.uploadFile(inputStream, rpcRequest.getEntity().getFileName());
        } catch (Exception e) {
            log.error("upload the file is error", e);
            return RpcResponse.failure("upload the file is error");
        }
        return RpcResponse.success();
    }

    @Override
    public RpcResponse<?> remove(RpcRequest<OssProcessDTO> rpcRequest)  {
        try {
            return minioService.deleteFile(rpcRequest.getEntity().getFileName()) ?
                    RpcResponse.success(): RpcResponse.failure("删除oss bucket文件失败！");
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            throw new RuntimeException("删除oss bucket文件失败！");
        }
    }

    @Override
    public RpcResponse<?> download(RpcRequest<OssProcessDTO> rpcRequest) {
        try(
                OutputStream outputStream =
                        new FileOutputStream(rpcRequest.getEntity().getFilePath());
        ) {
            minioService.downloadFile(rpcRequest.getEntity().getFileName());
        } catch (IOException e) {
            log.error("download file is failure!",e);
            return RpcResponse.failure("下载oss bucket文件失败！");
        } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException |
                InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            throw new RuntimeException("下载oss bucket文件失败！");
        }
        return RpcResponse.success(rpcRequest.getEntity().getFilePath());
    }
}
