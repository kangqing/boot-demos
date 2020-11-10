package com.yunqing.demoweblog.log;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 异常日志
 * @author yx
 * @since 2020/11/10 15:17
 */
@Data
public class ExceptionLog {

    private String id;

    private String logType;

    private String reqMethod;

    private String reqParam;

    private String responseResult;

    private String systemName;

    private String ModuleName;

    private String ip;

    private String uri;

    private String userId;

    private String mobile;

    private String excName;

    private String excMessage;

    private LocalDateTime createTime;
}
