package com.yunqing.api;

import com.yunqing.dto.OssProcessDTO;
import com.yunqing.dto.RpcRequest;
import com.yunqing.dto.RpcResponse;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author kangqing
 * @since 2023/8/4 08:21
 */
public interface OssOperateRpcApi {

    /**
     * bucket
     * @param rpcRequest:内部存储对应的BucketName
     * @return
     */
    RpcResponse<?> create(RpcRequest<String> rpcRequest) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    /**
     * 存储文件
     * @param rpcRequest filePath和bucketName
     * @return
     */
    RpcResponse<?> upload(RpcRequest<OssProcessDTO> rpcRequest);
    /**
     * 删除文件
     * @param rpcRequest bucketName 和 fileName
     * @return
     */
    RpcResponse<?> remove(RpcRequest<OssProcessDTO> rpcRequest) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    /**
     * 下载文件
     * @param rpcRequest bucketName 和 fileName
     * @return
     */
    RpcResponse<?> download(RpcRequest<OssProcessDTO> rpcRequest);

}
