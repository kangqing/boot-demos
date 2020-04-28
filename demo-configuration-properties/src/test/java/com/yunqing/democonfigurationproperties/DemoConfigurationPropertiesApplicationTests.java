package com.yunqing.democonfigurationproperties;

import cn.hutool.core.lang.Console;
import com.yunqing.democonfigurationproperties.config.PropertiesConst;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoConfigurationPropertiesApplicationTests {

    @Autowired
    private PropertiesConst propertiesConst;

    @Test
    void contextLoads() {
        /**
         * 测试配置在application.yml中的属性
         */
        System.out.println(propertiesConst.getStr() + "-----" + propertiesConst.getStrPassword());
        propertiesConst.getMyList().forEach(System.out::println);
        /**
         * 测试配置在mysql中的属性
         */
        Console.log("输出数据库中的configA配置的值={}", propertiesConst.getConfigA());
        Console.log("--------------------------{}", propertiesConst.getConfigB());
    }


    @Test
    void testStringUtilsLength() {
        String str = "你是你是你是你是啊啊1";
        if (StringUtils.length(str) > 10) {
            System.out.println("--------------------------------------------------");
        }
    }

}
