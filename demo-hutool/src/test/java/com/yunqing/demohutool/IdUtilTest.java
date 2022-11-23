package com.yunqing.demohutool;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kangqing
 * @since 2022/11/23 14:19
 */
@Slf4j
@SpringBootTest
public class IdUtilTest {


    @Test
    public void test() {
        log.info("Long类型 = {}", IdUtil.getSnowflakeNextId());
        log.info("String类型 = {}", IdUtil.getSnowflakeNextIdStr());


        // 第一个参数终端id, 第二个参数数据中心id
        final Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        log.info("-----{}", snowflake.nextId());
    }
}
