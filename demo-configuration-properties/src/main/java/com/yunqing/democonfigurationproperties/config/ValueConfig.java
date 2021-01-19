package com.yunqing.democonfigurationproperties.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;

/**
 * @author kangqing
 * @description
 * @date 2020/8/13 10:21
 */
@Configuration
@PropertySource(value = {"classpath:valueconfig.yml"}, factory = PropertySourceFactory.class)
public class ValueConfig {
    @Value("${yunqing.name}")
    private String name;

    private static String email;

    @Value("${yunqing.email}")
    public void setEmail(String email) {
        ValueConfig.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
}
