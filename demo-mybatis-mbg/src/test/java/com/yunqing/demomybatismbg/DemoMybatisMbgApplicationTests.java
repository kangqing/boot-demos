package com.yunqing.demomybatismbg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunqing.demomybatismbg.entity.User;
import com.yunqing.demomybatismbg.entity.UserExample;
import com.yunqing.demomybatismbg.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class DemoMybatisMbgApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * Example条件查询
     * select id, name, age, email, status from user WHERE ( name = ? and age between ? and ? ) or( status = ? )
     */
    @Test
    void select() {
        UserExample example = new UserExample();
        //当名字=Tom并且年龄在18-30之间   或者状态 = 1
        example.or().andNameEqualTo("Tom").andAgeBetween(18, 30);
        example.or().andStatusEqualTo("1");
        List<User> list =  userMapper.selectByExample(example);
        if (list.size() > 0) {
            list.forEach(e -> log.info("用户{}", e.toString()));
        }
    }

    /**
     * Example条件查询，分页
     * SELECT count(0) FROM user
     * select id, name, age, email, status from user LIMIT ?, ?
     */
    @Test
    void selectPage() {
        //查询第1页数据，每页3条数据
        PageHelper.startPage(1, 3);
        //不写条件 = 查所有
        List<User> list = userMapper.selectByExample(new UserExample());
        //用这个取到查询到的数据不分页的总数
        PageInfo<User> pageInfo = new PageInfo<>(list);
        if (list.size() > 0) {
            list.forEach(e -> log.info("用户{}", e.toString()));
            Assertions.assertTrue(pageInfo.getTotal() != 0);
            log.info("total = {}", pageInfo.getTotal());
        }
    }

    /**
     * insert添加,所有字段都会添加一遍，没有添加null
     * insert into user (name, age, email, status) values (?, ?, ?, ?)
     */
    @Test
    void insert() {
        User user = new User();
        user.setAge(26);
        user.setName("yunqing");
        user.setEmail("yunqing@qq.com");
        user.setStatus("1");
        Assertions.assertTrue(userMapper.insert(user) > 0);
        log.info("----- {}", user.toString());
    }

    /**
     * 有选择性的插入，只插入设置的字段
     * insert into user ( name, age ) values ( ?, ? )
     */
    @Test
    void insertSelective() {
        User user = new User();
        user.setAge(13);
        user.setName("xiaoming");
        Assertions.assertTrue(userMapper.insertSelective(user) > 0);
        log.info("----- {}", user.toString());
    }

    /**
     * 按条件删除
     * delete from user WHERE ( name = ? )
     */
    @Test
    void delete() {
        UserExample example = new UserExample();
        example.or().andNameEqualTo("yunqing");
        Assertions.assertTrue(userMapper.deleteByExample(example) > 0);
    }

    /**
     * 按条件有选择的更新
     * update user SET age = ? WHERE ( name = ? )
     */
    @Test
    void updateByExampleSelective() {
        User user = new User();
        user.setAge(30);
        UserExample example = new UserExample();
        example.or().andNameEqualTo("xiaoming");
        Assertions.assertTrue(userMapper.updateByExampleSelective(user, example) > 0);
    }

    /**
     * 全部字段都更新，没设置的更新为null
     * update user set id = ?, name = ?, age = ?, email = ?, status = ? WHERE ( name = ? )
     */
    @Test
    void update() {
        User user = new User();
        user.setId(3L);
        user.setName("xiaoming");
        user.setAge(20);
        user.setEmail("xiaom@qq.com");
        UserExample example = new UserExample();
        example.or().andNameEqualTo("xiaoming");
        Assertions.assertTrue(userMapper.updateByExample(user, example) > 0);
    }

}
