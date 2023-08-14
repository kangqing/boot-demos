1. 引入依赖
```xml
<dependency>
    <groupId>com.kangqing</groupId>
    <artifactId>xxl-job-register-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```
2. 配置属性

```properties
# 原本需要
xxl.job.admin.addresses=http://127.0.0.1:8080/xxl-job-admin
xxl.job.accessToken=default_token
xxl.job.executor.appname=xxl-job-executor-test
xxl.job.executor.address=
xxl.job.executor.ip=127.0.0.1
xxl.job.executor.port=9999
xxl.job.executor.logpath=/data/applogs/xxl-job/jobhandler
xxl.job.executor.logretentiondays=30

# register 自动注册需要
# admin用户名
xxl.job.admin.username=admin
# admin 密码
xxl.job.admin.password=123456
# 执行器名称
xxl.job.executor.title=test-title
```

3. 添加注解

```java
class Test {
    @XxlJob(value = "testJob")
    @XxlRegister(cron = "0 0 0 * * ? *",
            author = "XXL",
            jobDesc = "测试job")
    public void testJob(){
        System.out.println("#公众号：Java之康庄大道");
    }
}

```