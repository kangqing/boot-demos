package com.yunqing.service;

import com.yunqing.api.OssOperateRpcApi;
import com.yunqing.config.OssConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangqing
 * @since 2023/8/4 10:50
 */
public class OssRpcFactory {

    private static final Map<OssType, OssOperateRpcApi> map = new HashMap<>();
    static {
        map.put(OssType.MINIO, new MinioRpcOssService());
        map.put(OssType.ALIYUN, new AliyunRpcOssService());
        map.put(OssType.AMAZON_S3, new AmazonS3RpcOssService());
    }

    public static OssOperateRpcApi createOssOperateRpcApi(OssConfig ossConfig) {
        OssType ossType = ossConfig.getType();
        if (ossType == null) {
            ossType = OssType.MINIO;
        }
        final OssOperateRpcApi rpcApi = map.get(ossType);
        if (rpcApi == null) {
            throw new IllegalArgumentException("oss.type error, please select from MINIO or ALIYUN");
        }
        return rpcApi;
    }

    public enum OssType {
        MINIO,
        ALIYUN,
        AMAZON_S3;

        OssType() {
        }
    }
}
