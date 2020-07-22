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
    包括 User.java 和 Test1.java
   
### 1.2 数据库支持
        上面介绍的入门权限是通过shiro.ini文件获取的，但是在实际项目中，用户、角色、权限肯定是通过数据库进行
    维护的，所以本节介绍数据库的支持。
    1.RBAC概念
    RBAC 是当下权限系统的设计基础，同时有两种解释：
    1)： Role-Based Access Control，基于角色的访问控制
    即，你要能够删除产品，那么当前用户就必须拥有产品经理这个角色
    2)：Resource-Based Access Control，基于资源的访问控制
    即，你要能够删除产品，那么当前用户就必须拥有删除产品这样的权限
    