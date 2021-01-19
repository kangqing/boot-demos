package com.yunqing.demo.elasticsearch;

import lombok.Data;

import java.util.List;

/**
 * 自定义分页返回结果
 * @author kangqing
 * @since 2020/10/28 14:17
 */
@Data
public class PageResult<T> {

    private long total;
    private List<T> list;
}
