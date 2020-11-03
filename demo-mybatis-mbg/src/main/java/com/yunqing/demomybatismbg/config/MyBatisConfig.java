package com.yunqing.demomybatismbg.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 *
 * @author yunqing
 * @date 2019/4/8
 */
@Configuration
@MapperScan("com.yunqing.demomybatismbg.mapper")
public class MyBatisConfig {

}
