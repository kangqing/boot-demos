package com.yunqing.demomybatisplus.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yunqing
 * @since 2020/11/2 21:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDTO<T> extends Page<T> {

    /**
     * 第几页
     */
    private Integer page;

    /**
     * 页容量
     */
    private Integer limit;

}
