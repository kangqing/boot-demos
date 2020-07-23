import cn.hutool.core.lang.Console;
import entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import realm.DatabaseRealm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 对应README 1.1章节
 * @Author yx
 * @Data 2020/7/22 14:09
 */
public class ShiroTest {
    public static void main(String[] args) {
        //shiro1.1 - shiro-1.2测试方法
        //shiro1_2();

        //shiro1_3
        shiro1_3();

    }

    /**
     * shiro 1.3测试方法
     */
    private static void shiro1_3() {
        //注册一个用户
        //new JdbcConnection().createUser("bob", "123456");
        User user = new User(1,"bob", "123456");
        if (login(user)) {
            Console.log("{}登录成功，密码是{}", user.getUsername(), user.getPassword());
        } else {
            Console.log("{}登录失败，密码是{}", user.getUsername(), user.getPassword());
        }
    }

    /**
     * shiro1.1 - shiro1.2测试方法
     */
    private static void shiro1_2() {
        User user1 = new User(1,"zhang3", "12345");
        User user2 = new User(2,"li4", "abcde");
        User user3 = new User(3,"wang5", "11111");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        Console.log("封装用于验证登录的三个用户[{}], [{}], [{}]", user1.toString(), user2.toString(), user3.toString());
        Console.log("-------------------------------------------------------------------");
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
            if (login(user)) {
                Console.log("[{}]登录成功，所用的密码是[{}]", user.getUsername(), user.getPassword());
            } else {
                Console.log("[{}]登录失败，所用的密码是[{}]", user.getUsername(), user.getPassword());
            }
        }
        Console.log("-------------------------------------------------------------------");
        /**
         * 判断用户是否拥有角色列表中的角色
         */
        for (User user : userList) {
            for (String role : roles) {
                if (login(user)) {
                    if (hasRole(user, role)) {
                        Console.log("用户[{}]拥有角色：[{}]", user.getUsername(), role);
                    } else {
                        Console.log("用户[{}]不拥有角色：[{}]", user.getUsername(), role);
                    }
                }
            }
        }
        Console.log("-------------------------------------------------------------------");
        for (User user : userList) {
            for (String permit : permits) {
                if (login(user)) {
                    if (isPermitted(user, permit)) {
                        Console.log("用户[{}]拥有权限：[{}]", user.getUsername(), permit);
                    } else {
                        Console.log("用户[{}]不拥有权限：[{}]", user.getUsername(), permit);
                    }
                }
            }
        }
    }


    //----------------------------------------------------------------------------------------------

    /**
     * 获取Subject对象
     * @param user
     * @return
     */
    private static Subject getSubject(User user) {
        //创建一个DefaultSecurityManager实例
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //创建一个基于Ini文件的数据源，来认证授权
        //IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        //修改使用我们自定义的DatabaseRealm作为认证授权的依据
        DatabaseRealm databaseRealm = new DatabaseRealm();
        //数据源的数据绑定到DefaultSecurityManager实例
        defaultSecurityManager.setRealm(databaseRealm);
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
    private static boolean login(User user) {
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
    private static boolean hasRole(User user, String role) {
        Subject subject = getSubject(user);
        return subject.hasRole(role);
    }

    /**
     * 是否拥有某个权限
     * @param user
     * @param permit
     * @return
     */
    private static boolean isPermitted(User user, String permit) {
        Subject subject = getSubject(user);
        return subject.isPermitted(permit);
    }
}
