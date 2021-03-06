package com.yunqing.demomybatisplus.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yunqing
 * @date 2020/1/10 10:07
 * 雪花生成器,唯一主键id
 * -----------------------------------------
 * 注意:
 * 自3.3.0开始默认主键生成策略雪花生成器
 * type = IdType.ASSIGN_ID
 * 所以不用添加此配置类了
 *
 * -----------------------------------------
 */
@Configuration
public class IdConfig {

    @Bean
    public Snowflake snowflake() {

        return IdUtil.createSnowflake(1, 1);
    }

}
