package com.yunqing.democonfigurationproperties.config;

import com.yunqing.democonfigurationproperties.service.PropertiesConstConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

/**
 * 将常量配置到数据库中
 * @author kangqing
 * @date 2020/4/24 14:27
 */
@Configuration
@Order(1)
public class InitPropertiesDataFromMySQL {
    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private PropertiesConstConfigService propertiesConstConfigService;

    @PostConstruct
    public void initDatabasePropertySourceUsage() {
        // 获取系统属性集合
        MutablePropertySources propertySources = environment.getPropertySources();
        try {
            // 从数据库获取自定义变量列表
            Map<String, String> map = propertiesConstConfigService.getConfigMap();
            // 将转换后的列表加入属性中
            Properties properties = new Properties();
            properties.putAll(map);
            // 将属性转换为属性集合，并指定名称
            PropertiesPropertySource constants = new PropertiesPropertySource("properties-const-config", properties);
            // 没找到默认添加到第一位
            propertySources.addLast(constants);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
