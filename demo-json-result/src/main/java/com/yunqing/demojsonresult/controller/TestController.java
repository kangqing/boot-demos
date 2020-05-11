package com.yunqing.demojsonresult.controller;

import com.yunqing.demojsonresult.utils.JsonResult;
import com.yunqing.demojsonresult.utils.ResultCode;
import com.yunqing.demojsonresult.utils.ResultTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yx
 * @date 2020/5/9 14:01
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/result")
    public JsonResult test() throws Exception {
        String aa = "Hello, JsonResult";
        if (!aa.contains("1")) {
            throw new Exception("nnnnnn");
        }
        return ResultTool.success(aa);
    }
}
