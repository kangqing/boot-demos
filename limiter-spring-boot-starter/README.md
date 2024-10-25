## 1. 引入spring-boot-starter
```xml
<dependency>
    <groupId>com.yunqing</groupId>
    <artifactId>limiter-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 2.配置yml文件
```yaml
rate-limiter:
  ip-limits:
    defaultLimit: 5      # 没有配置的 IP 默认限流值（每秒允许 5 个请求）
    specific-ips:
      192.168.1.1: 10    # 对特定 IP 地址每秒允许 10 个请求
      192.168.1.2: 15    # 对特定 IP 地址每秒允许 15 个请求
  appkey-limits:
    defaultLimit: 10          # 没有配置的 AppKey 默认限流值
    specific-appkeys:
      appkey123: 20      # 对特定 AppKey 每秒允许 20 个请求
      appkey456: 30      # 对特定 AppKey 每秒允许 30 个请求
```

## 3.使用方式
接口上面添加注解@RateLimiter(target=默认ip, limit=1)

target:默认ip,可以是appKey
limit：接口限流数，注解里配置以注解配置的为准，注解不配置看配置文件，配置文件没配置则走配置文件默认值；

```java
@RateLimiter(target = "AppKey", limit = 3)
@GetMapping("/test")
public String test(HttpServletRequest request) {
    return "appKey";
}

@RateLimiter(limit = 2)
@GetMapping("/test1")
public String test1(HttpServletRequest request) {
    return "ip";
}
```

## 4. 注解里加一个单位时间，限流多少次

## 5. 加一个所有请求都记录的限制


