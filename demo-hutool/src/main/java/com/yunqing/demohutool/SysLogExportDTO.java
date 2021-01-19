package com.yunqing.demohutool;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志导出类
 * @author kangqing
 * @since 2021/1/4 16:37
 */
@Data
@Builder
public class SysLogExportDTO implements Serializable {

    private Long id;

    /**
     * 操作类型： 添加-1 删除-2 更新-3 查看-4
     */
    private String operation;

    /**
     * 操作人员名称
     */
    private String username;

    /**
     * 日志描述
     */
    private String logDesc;

    /**
     * 创建时间
     */
    private Date createTime;

}
