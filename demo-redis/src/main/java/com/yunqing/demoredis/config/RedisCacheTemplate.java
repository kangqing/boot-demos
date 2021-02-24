package com.yunqing.demoredis.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class RedisCacheTemplate implements CacheTemplate {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 防止出现 key 乱码的现象， redisTemplate默认使用的是JdkSerializationRedisSerializer
     * 把传入的值当Object对象进行序列化，所以可以猜测这里key出现的\xac\xed\x00\x05t\x00\tb其实可能是对象头信息。
     * @param redisTemplate
     */
    /*@Autowired(required = false)
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setEnableTransactionSupport(true);
        this.redisTemplate = redisTemplate;
    }*/

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
        return redisTemplate.keys(key);
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
     * 获取redis自增序号
     * @param key
     * @param delta 自增因子，要增加几
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
     * 获取redis自减序号
     * @param key
     * @param delta
     * @return
     */
    @Override
    public long decr(String key, long delta) {
        try {
            return redisTemplate.opsForValue().decrement(key, delta);
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
            return -1;
        }
    }

    /**
     * 获取redis自增序号
     * @param key
     * @param delta
     * @param time
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
     * 获取 hash 类型的集合
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
     * 添加类型为 hash 的键值对
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
     * 添加类型为 hash 的键值对，并且设置过期时间
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
     * 获取 value 类型为 hash 的键值对
     * @param key 键
     * @param item hash集合的键
     * @return 返回hash集合的值
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
     * 添加 value = Hash 类型键值对
     * @param key 键
     * @param item value的键
     * @param value value 的值
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
     * 添加 value = Hash 类型键值对
     * @param key 键
     * @param item value的键
     * @param value value 的值
     * @param time 过期时间
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
     * 获取 list 类型的值
     * @param key
     * @return
     */
    @Override
    public List<Object> lGet(String key) {
        return lGet(key, 0, -1);
    }

    /**
     * 获取 list 类型的两个索引之间的值
     * @param key 键
     * @param start 索引开始
     * @param end 索引结束
     * @return
     */
    @Override
    public List<Object> lGet(String key, long start, long end) {
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
     * 获取 list 中指定索引的值
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
     * 获取 list 的长度
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
     * 添加 list 类型数据
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
     * 添加 list类型数据并设置过期时间
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
     * 设置过期时间
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
