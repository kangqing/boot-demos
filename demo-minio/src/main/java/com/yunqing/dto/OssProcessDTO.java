package com.yunqing.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author kangqing
 * @since 2023/8/4 08:25
 */
@Builder
@Data
@SuppressWarnings("serial")
public class OssProcessDTO implements Serializable {

    private String filePath;
    private String bucketName;
    private String fileName;
    private MultipartFile file;

}

