package com.yunqing.demomybatisplus;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunqing.demomybatisplus.pojo.Role;
import com.yunqing.demomybatisplus.pojo.User;
import com.yunqing.demomybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class DemoMybatisPlusApplicationTests {

    @Autowired
    private Snowflake snowflake;
    @Autowired
    private UserService userService;

    /**
     * 测试有选择性的更新
     */
    @Test
    void testUpdateSelect() {
        User user = new User();
        //user.setId("1");
        user.setEmail("33333");
        /**
         * 是有选择性的更新
         * UPDATE t_user SET email=? WHERE id=?
         */
        userService.updateById(user);
        /**
         * 也是有选择性的更新
         * UPDATE t_user SET email=? WHERE (id = ?)
         */
        userService.update(user, new UpdateWrapper<User>().lambda().eq(User::getId, "1"));
        /**
         * 都是有选择性的更新
         * UPDATE t_user SET email=? WHERE (id = ?)
         */
        userService.update(new UpdateWrapper<User>().lambda().set(User::getEmail, "5555555").eq(User::getId, "1"));
    }

    /**
     * ActiveRecord添加， 有选择性的添加
     * INSERT INTO t_role ( id, role_code ) VALUES ( ?, ? )
     */
    @Test
    void insert() {
        Role role = new Role();
        role.setRoleCode("ROLE_user");
        role.setRoleName("用户");
        /**
         * 自3.3.0开始默认雪花生成器 type = IdType.ASSIGN_ID
         */
        //role.setId(snowflake.nextId());
        Assertions.assertTrue(role.insert());
        log.info(role.getId().toString());
    }

    /**
     * ActiveRecord修改
     * UPDATE t_role SET role_code=?, role_name=?, update_time=?, version=? WHERE id=? AND version=?
     *
     * UPDATE t_role SET create_time=? WHERE (id = ?)
     */
    @Test
    void update() {
        /**
         * 测试乐观锁更新Role,先查询获取版本号，在更新，之后再获取版本号
         */
        Role role = new Role().selectById(2L);
        log.info("更新之前获取版本号 = {}", role.getVersion());
        role.setRoleName("管理员1234");
        Assertions.assertTrue(role.updateById());
        Role role2 = new Role().selectById(2L);
        log.info("更新之后获取版本号 = {}", role2.getVersion());
        /**
         * 非乐观锁更新，直接更新
         */
        Assertions.assertTrue(new Role().update(new UpdateWrapper<Role>().lambda()
                .set(Role::getCreateTime, LocalDateTime.now()).eq(Role::getId, 1)));
    }

    /**
     * ActiveRecord查询
     * SELECT * FROM t_user WHERE (user_status = ?)
     *
     * SELECT * FROM t_user
     */
    @Test
    void select() {
        List<User> list = new User().selectList(new QueryWrapper<User>().lambda().eq(User::getUserStatus, "1"));
        List<String> emails = list.stream().map(User::getEmail).collect(Collectors.toList());
        Assertions.assertTrue(emails.contains("yunqing@qq.com"));

        list = new User().selectAll();
        Assertions.assertTrue(list.size() > 0);

        log.info(list.toString());
    }

    /**
     * ActiveRecord添加， 有选择性的添加
     * INSERT INTO t_user ( id, email, user_status, create_time ) VALUES ( ?, ?, ?, ? )
     */
    @Test
    void insertUser() {
        User user = new User();
        user.setEmail("new_user@qq.com");
        user.setUserStatus("1");
        user.setCreateTime(LocalDateTime.now());
        Assertions.assertTrue(user.insert());

        log.info(user.getId());
    }

    /**
     * ActiveRecord删除
     * DELETE FROM t_role WHERE id=?
     *
     * DELETE FROM t_user WHERE (user_status = ? AND create_time < ? AND email = ?)
     */
    @Test
    void delete() {
        Assertions.assertTrue(new Role().setId(2L).deleteById());

        Assertions.assertTrue(new User().delete(new QueryWrapper<User>().lambda().eq(User::getUserStatus, "1")
        .lt(User::getCreateTime, LocalDateTime.now()).eq(User::getEmail, "new_user@qq.com")));
    }

    /**
     * ActiveRecord分页查询
     * *******注意配置分页插件才能生效**********
     * SELECT COUNT(1) FROM t_user WHERE (user_status = ?)
     *
     * SELECT * FROM t_user WHERE (user_status = ?) LIMIT ?,?
     */
    @Test
    void selectPage() {
        //第一页，每页1条数据
        IPage<User> userIPage = new Page<>(1, 1);
        List<User> list = new User().selectPage(userIPage,
                new QueryWrapper<User>().lambda().eq(User::getUserStatus, "1"))
                .getRecords();
        //获取不分页总数
        System.out.println(userIPage.getTotal());
        list.forEach(System.out::println);
    }

    /**
     * 测试逻辑删除
     * UPDATE t_role SET deleted=1 WHERE id=? AND deleted=0
     */
    @Test
    void ljDeleted() {
        Assertions.assertTrue(new Role().setId(2L).deleteById());
    }

    /**
     * 测试查询，看看逻辑删除的数据是否还在
     */
    @Test
    void query() {
        List<Role> roles = new Role().selectAll();
        roles.forEach(System.out::println);
    }




}
