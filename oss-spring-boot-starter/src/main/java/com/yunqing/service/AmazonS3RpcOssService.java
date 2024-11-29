package com.yunqing.service;

import cn.hutool.core.io.IoUtil;
import com.yunqing.api.OssOperateRpcApi;
import com.yunqing.dto.OssProcessDTO;
import com.yunqing.dto.RpcRequest;
import com.yunqing.dto.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AmazonS3RpcOssService implements OssOperateRpcApi {

    @Resource
    private AmazonS3Service amazonS3Service;

//    @Override
//    public RpcResponse<?> create(RpcRequest<String> rpcRequest) {
//        try {
//            return amazonS3Service.createBucket(rpcRequest.getEntity()) ?
//                    RpcResponse.success() : RpcResponse.failure("创建 S3 bucket 失败！");
//        } catch (Exception e) {
//            log.error("创建 S3 bucket 失败！", e);
//            return RpcResponse.failure("创建 S3 bucket 失败！");
//        }
//    }

    @Override
    public RpcResponse<?> upload(RpcRequest<OssProcessDTO> rpcRequest) {
        try (InputStream inputStream =
                     rpcRequest.getEntity().getFile().getInputStream()) {
            amazonS3Service.uploadFile(inputStream, rpcRequest.getEntity().getFilePath(),
                    rpcRequest.getEntity().getFileName());
        } catch (Exception e) {
            log.error("upload the file is error", e);
            return RpcResponse.failure("upload the file is error");
        }
        return RpcResponse.success();
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
    public void download(RpcRequest<OssProcessDTO> rpcRequest, HttpServletResponse response) {
        try (InputStream inputStream = amazonS3Service.downloadFile(rpcRequest.getEntity().getFileName())) {
            // 设置响应头，指定文件下载的名称和类型
            String fileName = URLEncoder.encode(rpcRequest.getEntity().getFileName(), StandardCharsets.UTF_8);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            // 将输入流写入到响应的输出流
            IoUtil.copy(inputStream, response.getOutputStream());
            response.flushBuffer(); // 确保数据完全写入
        } catch (Exception e) {
            log.error("下载文件失败！", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
