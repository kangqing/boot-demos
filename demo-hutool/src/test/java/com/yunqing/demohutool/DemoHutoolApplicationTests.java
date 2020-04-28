package com.yunqing.demohutool;

import cn.hutool.core.lang.Console;
import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoHutoolApplicationTests {

    @Test
    void contextLoads() {
        Console.log("测试MD5:{}", SecureUtil.md5("123456"));
    }

}
