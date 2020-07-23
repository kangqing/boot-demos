## `shiro`从入门到精通
## 1.知识点前瞻：
    1. Authentication：身份认证/登录(账号密码验证)。
    2. Authorization：授权，即角色或者权限验证。
    3. Session Manager：会话管理，用户登录后的session相关管理。
    4. Cryptography：加密，密码加密等。
    5. Web Support：Web支持，集成Web环境。
    6. Caching：缓存，用户信息、角色、权限等缓存到如redis等缓存中。
    7. Concurrency：多线程并发验证，在一个线程中开启另一个线程，可以把权限自动传播过去。
    8. Testing：测试支持；
    9. Run As：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问。
    10. Remember Me：记住我，登录后，下次再来的话不用登录了。
### 1.1 `shiro`入门
        通过一个小栗子，了解一下 shiro 的魅力，这里通过shiro.ini文件定义了用户、角色、权限信息，涉及到的代码
    包括 User.java 和 ShiroTest.java
   
### 1.2 数据库支持
        上面介绍的入门权限是通过shiro.ini文件获取的，但是在实际项目中，用户、角色、权限肯定是通过数据库进行
    维护的，所以本节介绍数据库的支持。
    1.RBAC概念
    RBAC 是当下权限系统的设计基础，同时有两种解释：
    1)： Role-Based Access Control，基于角色的访问控制
    即，你要能够删除产品，那么当前用户就必须拥有产品经理这个角色
    2)：Resource-Based Access Control，基于资源的访问控制
    即，你要能够删除产品，那么当前用户就必须拥有删除产品这样的权限
    
    2.数据库结构和数据见user_role_permit.sql文件
    
    3.定义JdbcConnection.java用来查询用户密码和查询用户角色/权限，顺便复习JDBC
    
    4.定义真正用来认证、授权的类DatabaseRealm extends AuthorizingRealm
    Realm 在 Shiro里到底扮演什么角色呢？
     * 当应用程序向 Shiro 提供了 账号和密码之后， Shiro 就会问 Realm 这个账号密码是否对， 如果对的话，其所对应的用户拥有哪些角色，哪些权限。
     * 所以Realm 是什么？ 其实就是个中介。 Realm 得到了 Shiro 给的用户和密码后，有可能去找 ini 文件，也可以去找数据库。
     * Realm 就是干这个用的，它才是真正进行用户认证和授权的关键地方。
    5.修改之前ShiroTest的获取Subject对象的方法  
    主要修改了这两行：把认证授权由之前的shiro.ini决定，换成了DatabaseRealm类决定
    而DatabaseRealm类的认证授权是通过查询数据库而来的
    ```
    //创建一个基于Ini文件的数据源，来认证授权
    //IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
    //修改使用我们自定义的DatabaseRealm作为认证授权的依据
    DatabaseRealm databaseRealm = new DatabaseRealm();
    ```
### 1.3 `shiro`加密
    1.首先在JdbcConnection.java中添加了一个新增用户的方法,注意新增用户使用sha256加盐加密
    ```
        /**
         * 新建用户
         * @param username 用户名
         * @param password 密码
         * @return
         */
        public boolean createUser(String username, String password) {
            String sql = "insert into user values(null, ?, ?)";
            String salt = "csau8za98xa@^*&7==-??.>smjhd";
            System.out.println(salt);
            //盐值加密密码后，再进行存入数据库
            String encodePass = SecureUtil.sha256(password + salt);
            try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, encodePass);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    ```
    2.其次在DataRealm.java认证的时候，根据用户输入的密码同样进行sha256加盐加密之后，比对数据库中存的密码
    ```
    String salt = "csau8za98xa@^*&7==-??.>smjhd";
    String encodePass = SecureUtil.sha256(password + salt);
    //从数据库中获取用户名的密码
    String passwordInDB = new JdbcConnection().getPasswordByUsername(username);
    if (!encodePass.equals(passwordInDB) || StrUtil.isBlank(passwordInDB)) {
        throw new AuthenticationException();
    }
    ```
    3.在shiroTest.java测试方法中先，添加一个用户，再用此用户进行登录
    ```
    /**
     * shiro 1.3测试方法
     */
    private static void shiro1_3() {
        //注册一个用户
        //new JdbcConnection().createUser("bob", "123456");
        User user = User.builder().username("bob")
                .password("123456").build();
        if (login(user)) {
            Console.log("{}登录成功，密码是{}", user.getUsername(), user.getPassword());
        } else {
            Console.log("{}登录失败，密码是{}", user.getUsername(), user.getPassword());
        }
    }
    ```
    