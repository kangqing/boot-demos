package com.yunqing.demoshiro.test;

import com.google.common.collect.Lists;
import com.yunqing.demoshiro.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 对应README 1.1章节
 * @Author yx
 * @Data 2020/7/22 14:09
 */
@Slf4j
public class Test1 {
    public static void main(String[] args) {
        User user1 = new User(1,"zhang3", "12345");
        User user2 = User.builder().build();
        user2.setUsername("li4")
                .setPassword("abcde").setId(2);
        User user3 = User.builder()
                .username("wang5")
                .password("11111")
                .id(3)
                .build();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        log.info("封装用于验证登录的三个用户[{}], [{}], [{}]", user1.toString(), user2.toString(), user3.toString());
        log.info("-------------------------------------------------------------------");
        /**
         * 角色
         */
        List<String> roles = Arrays.asList("admin", "productManager");
        /**
         * 权限
         */
        List<String> permits = Arrays.asList("addProduct", "addOrder");

        /**
         * 判断list中的用户是否登录
         */
        for (User user : userList) {
            if (Solution.login(user)) {
                log.info("[{}]登录成功，所用的密码是[{}]", user.getUsername(), user.getPassword());
            } else {
                log.info("[{}]登录失败，所用的密码是[{}]", user.getUsername(), user.getPassword());
            }
        }
        log.info("-------------------------------------------------------------------");
        /**
         * 判断用户是否拥有角色列表中的角色
         */
        for (User user : userList) {
            for (String role : roles) {
                if (Solution.login(user)) {
                    if (Solution.hasRole(user, role)) {
                        log.info("用户[{}]拥有角色：[{}]", user.getUsername(), role);
                    } else {
                        log.info("用户[{}]不拥有角色：[{}]", user.getUsername(), role);
                    }
                }
            }
        }
        log.info("-------------------------------------------------------------------");
        for (User user : userList) {
            for (String permit : permits) {
                if (Solution.login(user)) {
                    if (Solution.isPermitted(user, permit)) {
                        log.info("用户[{}]拥有权限：[{}]", user.getUsername(), permit);
                    } else {
                        log.info("用户[{}]不拥有权限：[{}]", user.getUsername(), permit);
                    }
                }
            }
        }

    }
}

/**
 * 从 shiro.ini中获取用户、角色、权限
 */
class Solution {
    /**
     * 获取Subject对象
     * @param user
     * @return
     */
    public static Subject getSubject(User user) {
        //创建一个DefaultSecurityManager实例
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //创建一个基于Ini文件的数据源
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        //数据源的数据绑定到DefaultSecurityManager实例
        defaultSecurityManager.setRealm(iniRealm);
        //将安全管理者实例放入全局对象
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //全局对象通过安全管理者生成Subject对象并返回
        return SecurityUtils.getSubject();
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public static boolean login(User user) {
        Subject subject = getSubject(user);
        //如果已经登录，则退出
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        //封装用户的登录信息
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            //将封装的用户信息，传递到Realm中进行验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //异常则验证失败
            return false;
        }
        return subject.isAuthenticated();
    }

    /**
     * 是否拥有某个角色
     * @return
     */
    public static boolean hasRole(User user, String role) {
        Subject subject = getSubject(user);
        return subject.hasRole(role);
    }

    /**
     * 是否拥有某个权限
     * @param user
     * @param permit
     * @return
     */
    public static boolean isPermitted(User user, String permit) {
        Subject subject = getSubject(user);
        return subject.isPermitted(permit);
    }
}
