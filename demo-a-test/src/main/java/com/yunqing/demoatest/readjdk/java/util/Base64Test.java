package com.yunqing.demoatest.readjdk.java.util;

import cn.hutool.core.lang.Console;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 在Java 8中，Base64编码已经成为Java类库的标准。
 *
 * Java 8 内置了 Base64 编码的编码器和解码器。
 *
 * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
 *
 * 基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
 * URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
 * MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
 * @author kangqing
 * @since 2020/9/21 10:52
 */
public class Base64Test {
    /**
     * 主要应用场景：
     * 例如: url中有一个参数也是url，需要编码、解码
     * 例如:
     * @param args
     */
    public static void main(String[] args) {
        //编码，加密getEncoder()
        String str = Base64.getEncoder().encodeToString("java8_Base64?".getBytes(StandardCharsets.UTF_8));
        Console.log("标准加密之后的字符串是 {}", str);
        //解码，解密getDecoder()
        byte[] decode = Base64.getDecoder().decode(str);
        Console.log("标准解码 {}", new String(decode, StandardCharsets.UTF_8));

        //URL编码
        str = Base64.getUrlEncoder().encodeToString("dksiofdo+/d,s;".getBytes(StandardCharsets.UTF_8));
        Console.log("加密后字符串是：{}", str);
        //URL解码
        byte[] decode1 = Base64.getUrlDecoder().decode(str);
        Console.log("URL解码后 {}", new String(decode1, StandardCharsets.UTF_8));


        //Mime编码
        str = Base64.getMimeEncoder().encodeToString("dksiofdo+/d,s;ddd".getBytes(StandardCharsets.UTF_8));
        Console.log("加密后字符串是：{}", str);
        //Mime解码
        byte[] decode2 = Base64.getMimeDecoder().decode(str);
        Console.log("Mime解码后 {}", new String(decode2, StandardCharsets.UTF_8));

        /*
        Base64.Encoder getMimeEncoder(int lineLength, byte[] lineSeparator)：
        返回具有给定lineLength的已修改MIME变体的编码器（向下舍入到最接近的4的倍数 - 输出在lineLength<= 0 时不分成行）和lineSeparator。
        当lineSeparator包含RFC 2045的表1中列出的任何Base64字母字符时，它会抛出java.lang.IllegalArgumentException。

        相当于用lineSeparator隔开加密后的字符串,每lineLength（4的倍数向下取整）隔开
         */
        String s = Base64.getMimeEncoder(6, ".?--".getBytes())
                .encodeToString("jimidssafsaa".getBytes(StandardCharsets.UTF_8));
        Console.log("加密后 {}", s);
        byte[] decode3 = Base64.getMimeDecoder().decode(s);
        Console.log("Mime解码后 {}", new String(decode3, StandardCharsets.UTF_8));


        //静态内部类Encoder
        //静态内部类Decoder

    }
}
