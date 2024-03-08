package creative.factory.abstractfactory01.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟redis集群IIR操作
 * @author kangqing
 * @since 2024/3/7 22:29
 */
@Slf4j
public class IIRUtil {
    private final Map<String, String> dataMap = new ConcurrentHashMap<>();

    public void setIIR(String key, String val) {
        log.info("IIR集群模式模拟set操作，key = {}, value = {}", key, val);
        dataMap.put(key, val);
    }

    public String getIIR(String key) {
        log.info("IIR集群模式模拟get操作，key = {}", key);
        return dataMap.get(key);
    }

    public void delIIR(String key) {
        log.info("IIR集群模式模拟del操作，key = {}", key);
        dataMap.remove(key);
    }
}
