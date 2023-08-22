package com.kangqing.serial.bean;

import com.alicp.jetcache.CacheValueHolder;
import com.alicp.jetcache.anno.SerialPolicy;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.function.Function;

/**
 * @author kangqing
 * @since 2023/8/22 08:24
 */
public class JxJsonSerialPolicy implements SerialPolicy {


    private Jackson2JsonRedisSerializer<CacheValueHolder> jackson2JsonRedisSerializer;

    public void setJackson2JsonRedisSerializer(Jackson2JsonRedisSerializer<CacheValueHolder> jackson2JsonRedisSerializer) {
        this.jackson2JsonRedisSerializer = jackson2JsonRedisSerializer;
    }

    @Override
    public Function<Object, byte[]> encoder() {
        return (value) -> jackson2JsonRedisSerializer.serialize(value);
    }

    @Override
    public Function<byte[], Object> decoder() {
        return bytes -> jackson2JsonRedisSerializer.deserialize(bytes);
    }
}