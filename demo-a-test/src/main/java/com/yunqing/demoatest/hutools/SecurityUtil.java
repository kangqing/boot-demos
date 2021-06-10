package com.yunqing.demoatest.hutools;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * 加密算法
 *
 * @author kangqing
 * @since 2021/5/19 10:45
 */
public class SecurityUtil {
    public static void main(String[] args) {
        String content = "test中文";
        SymmetricCrypto sm4 = SmUtil.sm4("646974696E6754657374436F64654D4D".getBytes(StandardCharsets.UTF_8));

        String encryptHex = sm4.encryptHex(content);
        String decryptStr = sm4.decryptStr("92d9930ee0b992dbc855648edd8b96c3", CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr);
    }
}
