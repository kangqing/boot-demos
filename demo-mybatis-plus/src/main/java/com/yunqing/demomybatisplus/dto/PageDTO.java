package com.yunqing.demomybatisplus.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author yunqing
 * @since 2020/11/2 21:54
 */
@Data
public class PageDTO {

    /**
     * 第几页
     */
    @Min(value = 1, message = "页码不能小于1")
    private Integer page;

    /**
     * 页容量
     */
    @Min(value = 1, message = "页容量不能小于1")
    private Integer limit;


    /**
     * 构建分页信息
     * --------------------都传值 -1 可以不分页，查询所有----------------------------
     * @param pageDTO 分页信息
     * @return 返回IPage分页信息或者null
     */
    public static <T> IPage<T> buildPageCondition(PageDTO pageDTO) {
        IPage<T> page = new Page<>();
        if(pageDTO.getLimit() != null && pageDTO.getPage() != null) {
            page.setCurrent(pageDTO.getPage()).setSize(pageDTO.getLimit());
        } else {
            page.setCurrent(-1).setSize(-1);
        }
        return page;
    }



}
