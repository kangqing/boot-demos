package com.yunqing.demoatest.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 自定义类加载器
 * @author yx
 * @since 2020/10/16 11:12
 */
public class HelloClassLoader extends ClassLoader {

    /**
     * 程序的入口，Main函数
     * @param args
     */
    public static void main(String[] args) {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        try {
            Class<?> clazz = helloClassLoader.findClass("Hello");
            Object obj = clazz.newInstance();
            Method hello = clazz.getMethod("hello");
            hello.invoke(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遵循双亲委派原则，重写findClass方法
     * 如果不遵循，可以重写loadClass方法
     * @param name 返回类名
     * @return 返回一个类
     * @throws ClassNotFoundException 异常抛出
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("D:\\java\\gitProject\\boot-demos\\demo-a-test\\src\\main\\resources\\Hello.xlass");
        try {
            // 把文件转换成字节数组
            byte[] bytes = getClassBytes(file);
            // defineClass方法可以把二进制流字节组成的文件转换为一个java.lang.Class
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    /**
     * 获取文件的字节数组
     * @param file 原始文件
     * @return 返回字节数组
     * @throws Exception 异常抛出
     */
    private byte[] getClassBytes(File file) throws Exception {
        // 获取文件输入流
        FileInputStream fis = new FileInputStream(file);
        // 建立通道
        FileChannel fc = fis.getChannel();
        // 因为要获取.xlass的字节，所以使用字节流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        // 获取字节类型缓冲区，容量1024字节
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(byteBuffer);
            if (i == 0 || i == -1)
                break;
            // 切换读数据模式
            byteBuffer.flip();
            wbc.write(byteBuffer);
            byteBuffer.clear();
        }
        fis.close();
        // xlass文件是每个字节都用255减过一遍，所以要想恢复之前的class文件，需要执行下面的
        // 每个字节都用255去减，还原回之前的
        byte[] bytes = baos.toByteArray();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }
}