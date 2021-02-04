package com.yunqing.demoatest.multithread.jdk;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;


/**
 * JDK8 日期时间处理
 * @author kangqing
 * @since 2021/2/3 11:42
 */
public class JDKDateTime {
    public static void main(String[] args) throws Exception {
        //dateTime();
        jdk11();
        //get();
        //get1();
        //asyncPost();
        //proxy();


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


    private static void jdk11() throws IOException {
        var s = "world";
        var list = List.of(s, "java", "python", "go");
        list.stream().map(e -> "Hello, " + e)
                .forEach(System.out::println);

        /**
         * lambda表达式体为 true 打印，遇到 false则不再继续
         */
        list.stream().takeWhile(e -> !StrUtil.startWith(e, "p"))
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        /**
         * lambda表达式体为true不打印，一直到遇到false开始打印
         */
        list.stream().dropWhile(e -> !StrUtil.startWith(e, "p"))
                .forEach(System.out::println);

        // 还有个 iterate 方法的新重载方法，可以让你提供一个 Predicate (判断条件)来指定什么时候结束迭代：
        IntStream.iterate( 0 , i -> i < 10 , i -> i + 1 ).forEach(System.out::println);


    }

    /**
     * 将响应主体打印出来
     * @throws Exception
     */
    public static void get() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8084/hc/getTest"))
                .build();
        // 同步
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("同步" + response.statusCode());
        System.out.println("同步" + response.body());
    }

    public static void get1() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8084/hc/getTest"))
                .build();
        // 异步
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(r -> {
                    System.out.println(r.statusCode());
                    return r;
                }).thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    // 异步调用 POST
    public static void asyncPost() throws Exception {
        HttpClient client = HttpClient.newBuilder()
                // http1.1 or http2.0默认2.0
                .version(HttpClient.Version.HTTP_2)
                // 是否遵从服务器发出的重定向
                .followRedirects(HttpClient.Redirect.NORMAL)
                // 超时时间
                .connectTimeout(Duration.ofSeconds(20))
                // 代理
                // 身份认证
                // 等等
                .build();

        String jsonBody = JSONUtil.toJsonStr("HttpClient.....post");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://127.0.0.1:8084/hc/postTest"))
                .header("Content-Type" , "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(r -> {
                    System.out.println(r.statusCode());
                    return r;
                }).thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }




}

@Data
@AllArgsConstructor
@NoArgsConstructor
class  User {
    String name;
}