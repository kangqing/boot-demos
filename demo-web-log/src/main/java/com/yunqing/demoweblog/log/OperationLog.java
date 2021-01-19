package com.yunqing.demoweblog.log;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kangqing
 * @since 2020/11/10 14:36
 */
@Data
public class OperationLog {

    private String id;

    private String logType;

    private String operType;

    private String reqMethod;

    private String methodType;

    private Object reqParam;

    private String responseResult;

    private String systemName;

    private String ModuleName;

    private String ip;

    private String uri;

    private String userId;

    private String mobile;

    private String content;

    private LocalDateTime createTime;
}
