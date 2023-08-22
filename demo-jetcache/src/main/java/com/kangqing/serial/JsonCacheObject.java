package com.kangqing.serial;

import lombok.Data;

/**
 * @author kangqing
 * @since 2023/8/21 11:32
 */
@Data
public class JsonCacheObject<V> {

    private String className;
    private V realObj;

    public JsonCacheObject() {
    }

    public JsonCacheObject(String className, V realObj) {
        this.className = className;
        this.realObj = realObj;
    }
}