package com.yunqing;

import com.yunqing.api.OssOperateRpcApi;
import com.yunqing.dto.OssProcessDTO;
import com.yunqing.dto.RpcRequest;
import com.yunqing.dto.RpcResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/8/4 20:10
 */
@RestController
public class TestController {

    @Resource
    private OssOperateRpcApi operateRpcApi;

    @GetMapping("/create")
    public RpcResponse<?> createBucket(String name) {
        RpcRequest<String> rpcRequest = new RpcRequest<>(name);
        try {
            operateRpcApi.create(rpcRequest);
            return RpcResponse.success();
        } catch (Exception e) {
            throw new RuntimeException("创建 Bucket 失败！");
        }
    }

    @PostMapping("/upload")
    public RpcResponse<?> uploadFile(MultipartFile file) {

        OssProcessDTO dto = OssProcessDTO.builder()
                .file(file)
                .fileName(file.getOriginalFilename())
                .build();
        final RpcRequest<OssProcessDTO> request = new RpcRequest<>(dto);

        return operateRpcApi.upload(request);

    }




}
