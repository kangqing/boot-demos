package com.yunqing.service;

import cn.hutool.core.io.IoUtil;
import com.yunqing.api.OssOperateRpcApi;
import com.yunqing.dto.OssProcessDTO;
import com.yunqing.dto.RpcRequest;
import com.yunqing.dto.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.*;

/**
 * @author kangqing
 * @since 2023/8/4 09:55
 */
@Slf4j
public class AliyunRpcOssService implements OssOperateRpcApi {
    @Resource
    private AliyunService aliyunService;

    @Override
    public RpcResponse<?> create(RpcRequest<String> rpcRequest) {
        return aliyunService.createBucket(rpcRequest.getEntity()) ? RpcResponse.success():
                RpcResponse.failure("创建oss bucket失败！");
    }

    @Override
    public RpcResponse<?> upload(RpcRequest<OssProcessDTO> rpcRequest) {
        try (InputStream inputStream =
                     rpcRequest.getEntity().getFile().getInputStream();) {
            aliyunService.uploadFile(inputStream, rpcRequest.getEntity().getFileName());
        } catch (Exception e) {
            log.error("upload the file is error", e);
            return RpcResponse.failure("upload the file is error");
        }
        return RpcResponse.success();
    }

    @Override
    public RpcResponse<?> remove(RpcRequest<OssProcessDTO> rpcRequest) {
        return aliyunService.deleteFile(rpcRequest.getEntity().getFileName()) ?
                RpcResponse.success(): RpcResponse.failure("删除oss bucket文件失败！");
    }

    @Override
    public RpcResponse<?> download(RpcRequest<OssProcessDTO> rpcRequest) {
        try(
                OutputStream outputStream =
                        new FileOutputStream(rpcRequest.getEntity().getFilePath());
        ) {
            aliyunService.downloadFile(rpcRequest.getEntity().getFileName());
        } catch (IOException e) {
            log.error("download file is failure!",e);
            return RpcResponse.failure("下载oss bucket文件失败！");
        }
        return RpcResponse.success(rpcRequest.getEntity().getFilePath());
    }
}
