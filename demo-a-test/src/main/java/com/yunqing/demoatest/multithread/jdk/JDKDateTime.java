package com.yunqing.demoatest.multithread.jdk;

import cn.hutool.core.lang.Console;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * JDK8 日期时间处理
 * @author kangqing
 * @since 2021/2/3 11:42
 */
public class JDKDateTime {
    public static void main(String[] args) {
        dateTime();
    }

    private static void dateTime() {
        // Local自动定义本地时区
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Console.log("当前日期是{}, 时间是{}, 日期时间{}", localDate,
                localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 自定义
        LocalDate date = LocalDate.of(2021, Month.AUGUST, 1);
        Console.log("自定义日期{}", date);
        // 设置地区
        LocalDate seoulDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LocalTime seoulTime = LocalTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime seoul = LocalDateTime.now(ZoneOffset.of("+9"));
        Console.log("首尔日期{}, 首尔时间{}, 首尔日期时间{}", seoulDate,
                seoulTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                seoul.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 时间戳是指格林威治时间1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)起至现在的总秒数。
        Console.log("当前时间转毫秒数{}", localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        Console.log("格林威治时间{}， 北京时间{}", Instant.now(), localDateTime);

        /**
         * 时间差值
         */
        Duration duration = Duration.between(localDateTime, seoul);
        System.out.println(duration);
        /**
         * 计算日期间隔
         */
        Period period = Period.between(date, seoulDate);
        System.out.println(period);

    }
}
