package com.yunqing.demomybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunqing.demomybatisplus.pojo.Role;
import com.yunqing.demomybatisplus.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class DemoMybatisPlusApplicationTests {

    /**
     * ActiveRecord添加
     */
    @Test
    void contextLoads() {
        Role role = new Role();
        role.setRoleCode("ROLE_ADMIN");
        Assertions.assertTrue(role.insert());
        log.info(role.getId().toString());
    }

    /**
     * ActiveRecord修改
     */
    @Test
    void update() {
        Assertions.assertTrue(new Role().setId(2).setRoleName("管理员").updateById());

        Assertions.assertTrue(new Role().update(new UpdateWrapper<Role>().lambda()
                .set(Role::getCreateTime, LocalDateTime.now()).eq(Role::getId, 2)));
    }

    /**
     * ActiveRecord查询
     */
    @Test
    void select() {
        List<User> list = new User().selectList(new QueryWrapper<User>().lambda().eq(User::getUserStatus, "1"));
        List<String> emails = list.stream().map(User::getEmail).collect(Collectors.toList());
        Assertions.assertTrue(emails.contains("yunqing_71@163.com"));

        list = new User().selectAll();
        Assertions.assertTrue(list.size() > 0);

        log.info(list.toString());
    }

    /**
     * ActiveRecord删除
     */
    @Test
    void delete() {
        Assertions.assertTrue(new Role().setId(2).deleteById());

        Assertions.assertTrue(new User().delete(new QueryWrapper<User>().lambda().eq(User::getUserStatus, "1")
        .lt(User::getCreateTime, LocalDateTime.now()).eq(User::getEmail, "yunqing_71@163.com")));
    }

    /**
     * ActiveRecord分页查询
     * *******注意配置分页插件才能生效**********
     */
    @Test
    void selectPage() {
        List<User> list = new User().selectPage(new Page<>(1, 1),
                new QueryWrapper<User>().lambda().eq(User::getUserStatus, "1"))
                .getRecords();
        list.forEach(System.out::println);
    }


}
