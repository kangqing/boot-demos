package com.yunqing.demohutool;

import cn.hutool.core.util.ZipUtil;
import cn.hutool.extra.compress.CompressUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author kangqing
 * @since 2022/6/30 10:42
 */
public class ZipUtilTest {

    @Test
    void test1() {
        final String property = System.getProperty("user.dir");

        final File file = new File(property + "/test");

    }
}
