package creative.factory.abstractfactory01.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟redis集群模式
 * @author kangqing
 * @since 2024/3/7 22:28
 */
@Slf4j
public class EGMUtil {
    private final Map<String, String> dataMap = new ConcurrentHashMap<>();

    public void setEGM(String key, String val) {
        log.info("EGM集群模式模拟set操作，key = {}, value = {}", key, val);
        dataMap.put(key, val);
    }

    public String getEGM(String key) {
        log.info("EGM集群模式模拟get操作，key = {}", key);
        return dataMap.get(key);
    }

    public void delEGM(String key) {
        log.info("EGM集群模式模拟del操作，key = {}", key);
        dataMap.remove(key);
    }
}
