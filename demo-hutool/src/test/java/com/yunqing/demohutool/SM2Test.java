package com.yunqing.demohutool;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.ECKeyUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

/**
 * @author kangqing
 * @since 2022/10/28 06:56
 */
public class SM2Test {

    private static final String PUBLICKEY = "04d71dcc04efa37e22ad8617ac8c7f7004d0ea164678dbe399ad1619d015ce97bf8ca618da2ac8eab93af2b9b7007c64628c236ce3e6352d1bde5e3faab651247a";
    private static final String PRIVATEKEY = "00e24087d63ea5d1bcdeb09f616de85556c182cb79924addba8fe75364f8159790";

    public static void main(String[] args) throws Exception {
        final String encrypt = encrypt("1qa2ws3ed$$$");
        System.out.println(decrypt(encrypt));
    }

    private static void genKey() {
        SM2 sm2 = SmUtil.sm2();
        sm2.setMode(SM2Engine.Mode.C1C3C2);
        final byte[] privateKey = BCUtil.encodeECPrivateKey(sm2.getPrivateKey());
        final byte[] publicKey = ((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(false);
        System.out.println(HexUtil.encodeHexStr(publicKey));
        System.out.println(HexUtil.encodeHexStr(privateKey));
    }

    /**
     * 解密
     * @param str
     * @return
     * @throws Exception
     */
    public static String decrypt(String str) throws Exception {
        SM2 sm2 = SmUtil.sm2(ECKeyUtil.toSm2PrivateParams(PRIVATEKEY), ECKeyUtil.toSm2PublicParams(PUBLICKEY));
        sm2.setMode(SM2Engine.Mode.C1C3C2);
        // 要解密的参数
        String param;
        param = !str.startsWith("04") ? "04" + str : str;
        final byte[] decryptFromBcd = sm2.decryptFromBcd(param, KeyType.PrivateKey);
        if (decryptFromBcd == null || decryptFromBcd.length <= 0) {
            throw new Exception("解密失败");
        }
        return StrUtil.utf8Str(decryptFromBcd);
    }

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        SM2 sm2 = SmUtil.sm2(ECKeyUtil.toSm2PrivateParams(PRIVATEKEY), ECKeyUtil.toSm2PublicParams(PUBLICKEY));
        sm2.setMode(SM2Engine.Mode.C1C3C2);
        String encryptBcd = sm2.encryptBcd(str, KeyType.PublicKey);
        if (encryptBcd != null) {
            // 跟前端sm-crypto库调试时，前端解密时，不能携带04标志位，这里后端去掉04标志位
            if (encryptBcd.startsWith("04")) {
                encryptBcd = encryptBcd.substring(2);
            }
            // 前端解密时，只能解析小写形式，这里转化成小写
            encryptBcd = encryptBcd.toLowerCase();
        }
        return encryptBcd;
    }
}
