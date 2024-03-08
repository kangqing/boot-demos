package com.kangqing.demoeasyes;

import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EsMapperScan("com.kangqing.demoeasyes.mapper.es")
public class DemoEasyEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEasyEsApplication.class, args);
    }

}
