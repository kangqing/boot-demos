package com.yunqing.demoredis.controller;

import com.yunqing.demoredis.entity.Emp;
import com.yunqing.demoredis.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/selectAll")
    public List<Emp> selectAll() {
        return empService.selectAll();
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Emp emp) {
        empService.add(emp);
        return true;
    }

    @PostMapping("/delete")
    public boolean delete(@RequestParam String id) {
        empService.delete(id);
        return true;
    }
}
