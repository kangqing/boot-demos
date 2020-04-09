package com.yunqing.demoredis.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class RedisCacheTemplate implements CacheTemplate {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写入
     * @param key
     * @param value
     * @param expire 过期时间(秒)，传null不过期
     */
    @Override
    public boolean set(final String key, final String value, final Long expire) {
        return setObject(key, value, expire);
    }

    /**
     * 读取
     * @param key
     * @return
     */
    @Override
    public String get(final String key) {
        return (String) getObject(key);
    }

    /**
     * 写入
     * @param key
     * @param data
     */
    @Override
    public boolean setObject(final String key, final Object data) {
        if (key == null) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, data);
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + "失败", e);
            return false;
        }
    }

    /**
     * 写入
     * @param key
     * @param data
     * @param expire 过期时间(秒)，传null不过期
     */
    @Override
    public boolean setObject(final String key, final Object data, final long expire) {
        if (key == null) {
            return false;
        }
        try {
            ValueOperations<String, Object> opsForValue =
                    redisTemplate.opsForValue();
            if (expire > 0) {
                opsForValue.set(key, data, expire, TimeUnit.SECONDS);
            } else {
                opsForValue.set(key, data);
            }
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + "失败", e);
            return false;
        }
    }

    /**
     * 取对象（JAVA序列化）
     * @param key
     * @return
     */
    @Override
    public Object getObject(final String key) {
        if (key == null) {
            return null;
        }
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
        }
        return null;
    }

    /**
     * 删除指定key值
     * @param key
     */
    @Override
    public boolean delete(String key) {
        if (key == null) {
            return false;
        }
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            log.error("redis删除" + key + "失败", e);
            return false;
        }
    }

    /**
     * 查询key，传*时获取全部key
     * @param key
     * @return
     */
    @Override
    public Set<String> getKeys(String key) {
        if (key == null) {
            return null;
        }
        Set<String> set = redisTemplate.keys(key);
        return set;
    }

    /**
     * 删除所有
     */
    @Override
    public boolean deleteAll() {
        Set<String> keyList = this.getKeys("*");
        try {
            redisTemplate.delete(keyList);
            return true;
        } catch (Exception e) {
            log.error("清空redis失败", e);
            return false;
        }
    }

    /**
     * @Title: incr
     * @Description: 获取redis自增序号
     * @param key
     * @param delta
     * @return
     */
    @Override
    public long incr(String key, long delta) {
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
            return -1;
        }
    }

    /**
     * @Title: incr
     * @Description: 获取redis自增序号
     * @param key redis的 key值
     * @param delta 自增的增量
     * @return
     */
    @Override
    public long incr(String key, long delta, long time) {
        try {
            long l = redisTemplate.opsForValue().increment(key, delta);
            if (time > 0) {
                expire(key, time);
            }
            return l;
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
            return -1;
        }
    }

    /**
     * @Title: hMapGet
     * @Description: 获取Map
     * @param key
     * @return
     */
    @Override
    public Map<Object, Object> hGet(String key) {
        if (key == null) {
            return null;
        }
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
            return null;
        }
    }

    /**
     * @Title: hMapSet
     * @Description: 设置Map
     * @param key
     * @param map
     * @return
     */
    @Override
    public boolean hSet(String key, Map<? extends Object, ? extends Object> map) {
        if (key == null) {
            return false;
        }
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + "失败", e);
            return false;
        }
    }

    /**
     * @Title: hMapSet
     * @Description: 设置Map
     * @param key
     * @param map
     * @param time
     * @return
     */
    @Override
    public boolean hSet(String key, Map<? extends Object, ? extends Object> map, long time) {
        if (key == null) {
            return false;
        }
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + "失败", e);
            return false;
        }
    }

    /**
     * @Title: hget
     * @Description: 获取Map中的一个键值
     * @param key
     * @param item
     * @return
     */
    @Override
    public Object hGet(String key, String item) {
        if (key == null || item == null) {
            return null;
        }
        try {
            return redisTemplate.opsForHash().get(key, item);
        } catch (Exception e) {
            log.error("redis获取" + key + ":" + item + "失败", e);
            return null;
        }
    }

    /**
     * @Title: hset
     * @Description: 设置Map中一个键值
     * @param key
     * @param item
     * @param value
     * @return
     */
    @Override
    public boolean hSet(String key, String item, Object value) {
        if (key == null || item == null) {
            return false;
        }
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + ":" + item + "失败", e);
            return false;
        }
    }

    /**
     * @Title: hset
     * @Description: 设置Map中一个键值
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    @Override
    public boolean hSet(String key, String item, Object value, long time) {
        if (key == null || item == null) {
            return false;
        }
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + ":" + item + "失败", e);
            return false;
        }

    }

    /**
     * @Title: lGet
     * @Description: 获取List全部对象
     * @param key
     * @return
     */
    @Override
    public List<Object> lGet(String key) {
        return lGet(key, 0, -1);
    }

    /**
     * @Title: lGet
     * @Description: 获取List中start到end的对象
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public List lGet(String key, long start, long end) {
        if (key == null) {
            return null;
        }
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
            return null;
        }
    }

    /**
     * @Title: lGetIndex
     * @Description: 获取list中指定index的c
     * @param key
     * @param index
     * @return
     */
    @Override
    public Object lGetIndex(String key, long index) {
        if (key == null) {
            return null;
        }
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
        }
        return null;
    }

    /**
     * @Title: lGetListSize
     * @Description: 获取List长度
     * @param key
     * @return
     */
    @Override
    public long lGetListSize(String key) {
        if (key == null) {
            return 0;
        }
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
            return 0;
        }
    }

    /**
     * @Title: lSet
     * @Description: 将List放入缓存
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean lSet(String key, List<Object> value) {
        if (key == null) {
            return false;
        }
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + "失败", e);
            return false;
        }
    }

    /**
     * @Title: lSet
     * @Description: 将List放入缓存
     * @param key
     * @param value
     * @param time
     * @return
     */
    @Override
    public boolean lSet(String key, List<Object> value, long time) {
        if (key == null) {
            return false;
        }
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + "失败", e);
            return false;
        }
    }

    /**
     * @Title: expire
     * @Description: 设置过期时间
     * @param key
     * @param time
     * @return
     */
    @Override
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + "过期时间失败", e);
            return false;
        }
    }

}
