package model;

import lombok.Data;

/**
 * 假设服务器收到请求并返回乘以 2 的intValue。响应将具有单个 int 值：
 * @author kangqing
 * @since 2023/4/2 17:11
 */
@Data
public class ResponseData {
    private Integer intValue;
    private String xxx;
}
