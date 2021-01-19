package com.yunqing.demoweblog.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangqing
 * @date 2020/5/6 10:12
 */
@RestController
@RequestMapping("/aoplog")
public class WebLogController {
    @GetMapping("/book")
    @ApiOperation("根据id查询书名")
    public String log(@RequestParam String id) {
        return StrUtil.format("id为{}的书是{}", id, "mysql必知必会");
    }

}
