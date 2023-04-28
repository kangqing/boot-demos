package com.yunqing.demoatest.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重试机制
 * @author kangqing
 * @since 2023/4/24 06:20
 */
public class ScheduleRetry {

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        final ScheduleRetry scheduleRetry = new ScheduleRetry();
        scheduleRetry.getExec();
    }

    AtomicInteger integer = new AtomicInteger(0);
    public void getExec() {
        System.out.println("---");
        if (integer.get() > 5) {
            integer.set(0);
            return;
        }
        integer.incrementAndGet();
        executorService.scheduleAtFixedRate(this::getExec, 0, 2, TimeUnit.SECONDS);
    }
}
