package com.yunqing.demomybatisplus.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yunqing
 * @date 2020/1/10 10:07
 * 雪花生成器,唯一主键id
 */
@Configuration
public class IdConfig {

    @Bean
    public Snowflake snowflake() {

        return IdUtil.createSnowflake(1, 1);
    }

}
