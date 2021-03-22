package com.yunqing.demoatest.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举单例，反射不能破坏单例
 * @author kangqing
 * @since 2021/3/22 08:54
 */
public enum EnumSingleton {
    INSTANCE;
    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 试图用反射破坏单例
        EnumSingleton instance = EnumSingleton.getInstance();
        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);// 破坏私有权限
        EnumSingleton instance1 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance);
        // java.lang.NoSuchMethodException: com.yunqing.demoatest.singleton.EnumSingleton.<init>()
        // 报错没有空参构造器
        // jad 反编译class 文件发现并不是无参数构造器，需要string.class 和 int.class

        //  Cannot reflectively create enum objects
        // 正确得到不允许使用反射破坏枚举单例
    }
}
