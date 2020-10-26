package com.yunqing.demojsonresult.controller;

import cn.hutool.core.util.StrUtil;
import com.yunqing.demojsonresult.User;
import com.yunqing.demojsonresult.exception.BaseException;
import com.yunqing.demojsonresult.utils.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author yx
 * @since 2020/5/9 14:01
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/query")
    public JsonResult<?> test() {
        String aa = "Hello, JsonResult";
        return JsonResult.success(aa);
    }

    @PostMapping("/add")
    public JsonResult<?> test1(@RequestParam String aa) {

        throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), aa);

    }

    @PostMapping("/update")
    public JsonResult<?> test2(@RequestBody User user) {
        //Assert.notNull(user.getUsername(), "用户名不能为空");
        if (user.getUsername()==null) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "用户名不能为空777");
        }
        return JsonResult.success(StrUtil.format("接收到的参数中用户名是{}", user.getUsername()));
    }

    @GetMapping("/delete")
    public JsonResult<?> test3(@RequestParam String id) {
        return JsonResult.success(StrUtil.format("id为{}的列不存在", id));
    }
}
