package com.kangqing.serial;

import com.alicp.jetcache.CacheValueHolder;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangqing.serial.bean.JxJsonSerialPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.function.Function;

/**
 * @author kangqing
 * @since 2023/8/21 11:33
 */
@Configuration
public class JetCacheConfig {

    /**
     * 和 java/kryo 一样 myJson 方式
     * @return
     */
    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider() {
            @Override
            public Function<byte[], Object> parseValueDecoder(String valueDecoder) {
                if (valueDecoder.equalsIgnoreCase("myJson")) {
                    return new JsonSerialPolicy().decoder();
                }
                return super.parseValueDecoder(valueDecoder);
            }

            @Override
            public Function<Object, byte[]> parseValueEncoder(String valueEncoder) {
                if (valueEncoder.equalsIgnoreCase("myJson")) {
                    return new JsonSerialPolicy().encoder();
                }
                return super.parseValueEncoder(valueEncoder);
            }
        };
    }


    /**
     * bean:bean:cacheJackson2 方式
     * @return
     */
    @Bean(name = "cacheJackson2")
    JxJsonSerialPolicy jxJsonSerialPolicy() {
        Jackson2JsonRedisSerializer<CacheValueHolder> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(CacheValueHolder.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        JxJsonSerialPolicy serialPolicy = new JxJsonSerialPolicy();
        serialPolicy.setJackson2JsonRedisSerializer(jackson2JsonRedisSerializer);
        return serialPolicy;
    }
}
