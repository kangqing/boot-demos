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
import javax.servlet.http.HttpServletResponse;

/**
 * @author kangqing
 * @since 2023/8/4 20:10
 */
@RestController
public class TestController {

    @Resource
    private OssOperateRpcApi operateRpcApi;


    @PostMapping("/upload")
    public RpcResponse<?> uploadFile(MultipartFile file) {

        OssProcessDTO dto = OssProcessDTO.builder()
                .file(file)
                .fileName(file.getOriginalFilename())
                .build();
        final RpcRequest<OssProcessDTO> request = new RpcRequest<>(dto);

        return operateRpcApi.upload(request);

    }

    @PostMapping("/checkFileExsit")
    public RpcResponse<?> checkFileExsit(String fileName) {
        OssProcessDTO dto = OssProcessDTO.builder()
                .fileName(fileName)
                .build();
        final RpcRequest<OssProcessDTO> request = new RpcRequest<>(dto);
        return operateRpcApi.checkFileExist(request);
    }

    @PostMapping("/download")
    public void downloadFile(String fileName, HttpServletResponse response) {
        OssProcessDTO dto = OssProcessDTO.builder()
                .fileName(fileName)
                .build();
        final RpcRequest<OssProcessDTO> request = new RpcRequest<>(dto);
        operateRpcApi.download(request, response);
    }




}
