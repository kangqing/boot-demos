package creative.factory.abstractfactory01.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 模拟单机redis
 * @author kangqing
 * @since 2024/3/7 22:21
 */
@Slf4j
public class RedisUtil {
    private final Map<String, String> dataMap = new ConcurrentHashMap<>();

    public void set(String key, String val) {
        log.info("单机模式模拟set操作，key = {}, value = {}", key, val);
        dataMap.put(key, val);
    }

    public String get(String key) {
        log.info("单机模式模拟get操作，key = {}", key);
        return dataMap.get(key);
    }

    public void del(String key) {
        log.info("单机模式模拟del操作，key = {}", key);
        dataMap.remove(key);
    }

}
