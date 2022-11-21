package com.yunqing.democonfigurationproperties.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author kangqing
 * @since 2022/11/21 19:00
 */
@Slf4j
@Component
public class TestScheduling {

    @Autowired
    private PropertiesConst propertiesConst;


    /**
     * 测试定时任务
     */
    @Scheduled(cron = "${com.yunqing.cronStr}")
    public void test() {
        log.info("打印定时任务执行，时间 = {}", LocalDateTime.now());
    }
}
