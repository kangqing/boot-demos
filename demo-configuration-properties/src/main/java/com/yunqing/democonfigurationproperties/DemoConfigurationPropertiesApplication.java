package com.yunqing.democonfigurationproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DemoConfigurationPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConfigurationPropertiesApplication.class, args);
    }

}
