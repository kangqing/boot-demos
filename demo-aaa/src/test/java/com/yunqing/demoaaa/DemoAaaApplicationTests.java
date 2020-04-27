package com.yunqing.demoaaa;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoAaaApplicationTests {

    @Test
    void contextLoads() {
        boolean b = StrUtil.length("123") > 1;
        if (b) {
            Console.log("测试{}", true);
        }
    }

}
