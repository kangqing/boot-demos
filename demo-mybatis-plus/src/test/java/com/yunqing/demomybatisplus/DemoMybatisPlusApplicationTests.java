package com.yunqing.demomybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yunqing.demomybatisplus.pojo.Role;
import com.yunqing.demomybatisplus.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
     * 修改
     */
    @Test
    void update() {
        Assertions.assertTrue(new Role().setId(2).setRoleName("管理员").updateById());

        Assertions.assertTrue(new Role().update(new UpdateWrapper<Role>().lambda()
                .set(Role::getCreateTime, LocalDateTime.now()).eq(Role::getId, 2)));
    }

}
