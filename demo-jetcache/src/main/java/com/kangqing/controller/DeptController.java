package com.kangqing.controller;

import com.kangqing.JsonResult.JsonResult;
import com.kangqing.entity.Dept;
import com.kangqing.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/8/21 09:51
 */
@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController {


    @Resource
    private DeptService deptService;

    @GetMapping("/get/{id}")
    public JsonResult<?> findById(@PathVariable Long id) {
        log.info("info 级别能打印, 日志路径 = {}", System.getProperty("user.home"));
        log.debug("debug级别打印不出来");
        Dept dept = deptService.findById(id);
        return new JsonResult<>(dept);
    }

    @PostMapping("update")
    public JsonResult<?> update(@RequestBody Dept dept) {
        return new JsonResult<>(deptService.update(dept));
    }

    @PostMapping("del")
    public JsonResult<?> delete(Long id) {
        return new JsonResult<>(deptService.del(id));
    }

    @PostMapping("add")
    public void add(@RequestBody Dept dept) {
        deptService.add(dept);
    }
}
