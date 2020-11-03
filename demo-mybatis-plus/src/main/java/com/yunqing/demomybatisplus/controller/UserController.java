package com.yunqing.demomybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunqing.demojsonresult.utils.JsonResult;
import com.yunqing.demomybatisplus.dto.PageDTO;
import com.yunqing.demomybatisplus.pojo.User;
import com.yunqing.demomybatisplus.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yunqing
 * @since 2020-01-09
 */
@Data
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/pageList")
    @Cacheable(value="userPageList")
    public Map<String, Object> pageList()  {
        System.out.println("输出此句话则没缓存，查询的数据库");
        //第一页，每页1条数据
        IPage<User> userIPage = new Page<>(1, 1);
        List<User> list = new User().selectPage(userIPage,
                new QueryWrapper<User>().lambda().eq(User::getUserStatus, "1"))
                .getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("pageList", list);
        return map;
    }

    @GetMapping("/list")
    public JsonResult<?> pageSearch(@ModelAttribute PageDTO pageDTO) {
        IPage<User> page = new Page<>(pageDTO.getPage(), pageDTO.getLimit());
        IPage<User> res = userService.page(page);
        return JsonResult.success(res);
    }

    /**
     * 连表查询分页, sql
     * @return
     */
    @GetMapping("/getAll")
    public JsonResult<?> pageCondition(@ModelAttribute PageDTO pageDTO) {
        return JsonResult.success(userService.getAll(pageDTO));
    }

    /**
     * 根据Email模糊条件查询分页
     * @param pageDTO 分页条件
     * @param email email
     * @return
     */
    @GetMapping("/getListByEmail")
    public JsonResult<?> wrapper(@ModelAttribute PageDTO pageDTO, @RequestParam String email) {
        return JsonResult.success(userService.getListByEmail(pageDTO, email));
    }


}
