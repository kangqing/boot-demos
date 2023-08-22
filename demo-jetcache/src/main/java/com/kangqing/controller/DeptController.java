package com.kangqing.controller;

import com.kangqing.JsonResult.JsonResult;
import com.kangqing.entity.Dept;
import com.kangqing.service.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/8/21 09:51
 */
@Slf4j
@Tag(name = "部门模块")
@RestController
@RequestMapping("/dept")
public class DeptController {


    @Resource
    private DeptService deptService;

    @Parameter(name = "id", description = "部门id", required = true, in = ParameterIn.PATH)
    @Operation(summary = "根据id查询部门")
    @GetMapping("/get/{id}")
    public JsonResult<?> findById(@PathVariable Long id) {
        log.info("info 级别能打印, 日志路径 = {}", System.getProperty("user.home"));
        log.debug("debug级别打印不出来");
        Dept dept = deptService.findById(id);
        return new JsonResult<>(dept);
    }

    @Operation(summary = "更新部门")
    @PostMapping("update")
    public JsonResult<?> update(@RequestBody Dept dept) {
        return new JsonResult<>(deptService.update(dept));
    }

    @Parameter(name = "id", description = "部门id", required = true, in = ParameterIn.DEFAULT)
    @Operation(summary = "删除部门")
    @PostMapping("del")
    public JsonResult<?> delete(Long id) {
        return new JsonResult<>(deptService.del(id));
    }

    @Operation(summary = "新增部门, 主键自增")
    @PostMapping("add")
    public void add(@RequestBody Dept dept) {
        deptService.add(dept);
    }
}
