# boot-demos 其中包括的项目简介:

## demo-a-test
建立本项目的目的是尝试一些新鲜的技术栈，或者正在归纳的一些知识点，而这些知识点又不足以新建一个项目来记录，反正就是杂七杂八，不成体系的一小demo

## demo-configuration-properties
一个可以从sql中、application.yml或者*.yml中读取配置文件的例子，包括@Value读取、@ConfigurationProperties读取的demo

## demo-distributed-lock
以一个超卖的问题演示分布式锁的使用，案例内包含本地锁演示出现超卖问题，redisson实现分布式锁解决问题和框架 Lock4j 注解（底层 Redisson）解决超卖问题；

## demo-elasticsearch
一个小demo可以使用ES进行增删改查，多条件分页查询，ElasticSearchRestTemplate模板的使用，聚合查询的例子等

## demo-hutool
hutool是一个小而全的java工具集，建立此demo用来测试hutool中的方法的使用

## demo-jetcache
logback-spring.xml 打印日志
整合阿里 jetcache 实现 caffeine:一级缓存， redis:二级缓存;
整合 Knife4j 替代 swagger 实现接口文档，替代注解如下：
![](https://yunqing-img.oss-cn-beijing.aliyuncs.com/hexo/article/202303/6B3KxC.png)

## demo-json-result
一个统一返回结果和统一异常处理的小demo

## demo-juc
关于 jdk 中 juc包下一些类的使用测试；

## demo-leetcode
本人刷力扣题的一些题目，具体题目见力扣官网，这里只记载了题号和解题方法

## demo-minio
开始想要实现 Springboot整合MINIO 使用的demo案例，实现之后又想要集成阿里云OSS对象存储，然后搞了个 oss-spring-boot-starter,整合MINIO和阿里云OSS；引入此starter可以在yml中配置使用 MINIO 或者 阿里云对象存储；

内部仅调通具体整合代码，实际上传、下载等API调用只调通了一个，使用时要注意；

## demo-mybatis-mbg
mybatis逆向工程生成代码包括Example

## demo-mybatis-plus
关于mybatis plus的一些使用方法总结的demo

## demo-netty
关于学习 springboot 整合 netty实现通讯的一个简单demo;

## demo-oss
测试使用上面的 oss-spring-boot-starter 进行MINIO 或者 阿里云对象存储的实际上传；

## demo-rabbitmq
springboot 整合 rabbitmq 使用案例；

## demo-redis
一个记录redis使用的demo

## demo-redisson
使用 redisson 实现分布式锁；AOP + 注解 方式解决加锁、解锁重复代码，实现一个注解，加锁解锁的共用代码由 AOP 切面完成；
模拟已知开源框架 Lock4j 实现分布式锁；

## demo-spring
关于 spring 的生命周期，关键类的测试代码；

## demo-spring-tx
关于本地事务的生效、失效调试；测试 this 调用本地事务失效等情况；

## demo-swagger
springboot整合swagger的starter, 新的丝袜哥的api接口地址 http://localhost:8080/swagger-ui/

## demo-web-log
一个记录日志输出的demo

## demo-xxl-register-starter
我们在使用 xxl_job 的过程中，假如需要多个定时任务，一个一个的手动注册到调度端十分麻烦，开发 register-xxl-job-starter 通过一个注解，直接在执行端的定时任务的业务逻辑方法上添加一个注解，自动注册定时任务到调度端；

## design-pattern
重新学习设计模式；包括模板方法模式、策略模式、工厂模式、观察者模式、建造者模式、享元模式、适配器模式等；