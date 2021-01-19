package com.yunqing.democonfigurationproperties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * @author kangqing
 * @date 2020/4/24 14:14
 */
@Configuration
@ConfigurationProperties(prefix = "com.yunqing")
@Data
public class PropertiesConst {
    //属性配置在application.yml中
    private String str;
    private String strPassword;
    private List<String> myList;
    //下面的属性配置在mysql中
    private String configA;
    private String configB;
}
