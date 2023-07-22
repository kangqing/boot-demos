package com.yunqing.controller;

import com.yunqing.service.GoodsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/22 13:29
 */
@RestController
public class TestController {

    @Resource
    private GoodsServiceImpl goodsService;

    @GetMapping("/test")
    public void test() throws Exception {
        goodsService.lockTest1(1001);
    }
}
