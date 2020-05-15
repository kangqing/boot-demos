package com.yunqing.demojsonresult.controller;

import cn.hutool.core.util.StrUtil;
import com.yunqing.demojsonresult.User;
import com.yunqing.demojsonresult.utils.JsonResult;
import com.yunqing.demojsonresult.utils.ResultTool;
import org.springframework.web.bind.annotation.*;

/**
 * @author yx
 * @date 2020/5/9 14:01
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/query")
    public JsonResult test() {
        String aa = "Hello, JsonResult";
        return ResultTool.success(aa);
    }

    @PostMapping("/add")
    public JsonResult test1(@RequestParam String aa) {
        return ResultTool.success(aa);
    }

    @PostMapping("/update")
    public JsonResult test2(@RequestBody User user) {
        return ResultTool.success(StrUtil.format("接收到的参数中用户名是{}", user.getUsername()));
    }

    @GetMapping("/delete")
    public JsonResult test3(@RequestParam String id) {
        return ResultTool.success(StrUtil.format("id为{}的列不存在", id));
    }
}
