package com.yunqing.demoredis.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CacheTemplate {

    boolean set(String key, String value, Long expire);

    String get(String key);

    boolean setObject(String key, Object data, long expire);

    Object getObject(String key);

    boolean delete(String key);

    boolean deleteAll();

    long incr(String key, long delta);

    long incr(String key, long delta, long time);

    Map<Object, Object> hGet(String key);

    boolean hSet(String key, Map<? extends Object, ? extends Object> map);

    boolean hSet(String key, Map<? extends Object, ? extends Object> map,
                 long time);

    Object hGet(String key, String item);

    boolean hSet(String key, String item, Object value);

    boolean hSet(String key, String item, Object value, long time);

    List<Object> lGet(String key);

    List<Object> lGet(String key, long start, long end);

    Object lGetIndex(String key, long index);

    long lGetListSize(String key);

    boolean lSet(String key, List<Object> value);

    boolean lSet(String key, List<Object> value, long time);

    boolean expire(String key, long time);

    Set<String> getKeys(String key);

    boolean setObject(String key, Object data);

}
