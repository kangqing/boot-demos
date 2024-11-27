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
        try (InputStream inputStream = aliyunService.downloadFile(rpcRequest.getEntity().getFileName());
             OutputStream outputStream = new FileOutputStream(rpcRequest.getEntity().getFilePath())) {
            IoUtil.copy(inputStream, outputStream);
            return RpcResponse.success(rpcRequest.getEntity().getFilePath());
        } catch (Exception e) {
            log.error("下载文件失败！", e);
            return RpcResponse.failure("下载文件失败！");
        }
    }

    @Override
    public RpcResponse<?> checkFileExist(RpcRequest<OssProcessDTO> rpcRequest) {
        boolean b = aliyunService.checkFileExist(rpcRequest.getEntity().getFileName());
        return RpcResponse.success(b);
    }
}
