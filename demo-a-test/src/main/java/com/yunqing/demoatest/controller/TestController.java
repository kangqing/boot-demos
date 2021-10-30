package com.yunqing.demoatest.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangqing
 * @since 2021/9/26 18:53
 */
@RestController
@Scope(scopeName = "prototype")
public class TestController {

    private int num = 0;

    @GetMapping("/test1")
    public int addOne() {
        return ++num;
    }

    @GetMapping("/test2")
    public int addOneAgain() {
        return ++num;
    }
}
