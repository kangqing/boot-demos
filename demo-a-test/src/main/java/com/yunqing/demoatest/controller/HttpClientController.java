package com.yunqing.demoatest.controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 测试http client
 * @author kangqing
 * @since 2021/2/4 10:46
 */
@RestController
@RequestMapping("/hc")
public class HttpClientController {

    @PostMapping("/postTest")
    public String postStr(@RequestBody String name) {
        return "Hello, " + name;
    }

    @GetMapping("/getTest")
    public String getStr() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return "get ... httpclient";
    }

    @GetMapping("/testNow")
    public String getStrNow() {
        return "立刻返回结果";
    }
}
