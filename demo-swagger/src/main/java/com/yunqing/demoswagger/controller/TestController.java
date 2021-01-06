package com.yunqing.demoswagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangqing
 * @since 2021/1/6 21:45
 */
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test...";
    }

}
