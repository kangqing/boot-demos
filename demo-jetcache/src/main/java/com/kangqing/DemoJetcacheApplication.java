package com.kangqing;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kangqing
 * @since 2023/8/21 09:33
 * //JetCache主要通过@Cached和@CreateCache实现缓存，
 * //@Cached是在接口方法或者类方法上添加缓存，一般以参数为key，以返回值为value存入缓存中。
 * //@CreateCache是直接创建一个缓存实例，然后调用put(T key， T value)、get(T key)等方法实现缓存。
 * //@EnableCreateCacheAnnotation注解用于开启jetcache中@CreateCache注解，
 * //@EnableMethodCache(basePackages = “com.example.jetcache”)注解用于开启@cache注解
 */
@SpringBootApplication
@MapperScan("com.kangqing.mapper")
@EnableMethodCache(basePackages = "com.kangqing.service")
public class DemoJetcacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoJetcacheApplication.class, args);
    }
}
