package com.yunqing.democonfigurationproperties.utilsReadYaml;

import lombok.Setter;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

/**
 * 配置非spring管理的类读取yaml配置
 */
public class ConfigUtils {

    @Setter
    private static Environment environment;

    public static <T> T getConfigValue(String key, Class<T> targetType) {
        if (environment == null) {
            throw new IllegalStateException("Environment is not initialized");
        }
        return Binder.get(environment).bind(key, targetType).orElse(null);
    }
}
