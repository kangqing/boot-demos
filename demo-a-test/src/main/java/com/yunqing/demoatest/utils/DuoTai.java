package com.yunqing.demoatest.utils;

/**
 * @author kangqing
 * @since 2021/3/12 18:18
 */
public class DuoTai {
    public static void main(String[] args) {
        Animals a = new Cat();
        System.out.println(a.say());
        a = new Dog();
        System.out.println(a.say());
    }
}

// 定义动物类
class Animals {
    String say() {
        return "...";
    }
}
// 子类继承自动物类
class Cat extends Animals {
    @Override
    String say() {
        return "喵喵喵";
    }
}

// 子类继承自动物类
class Dog extends Animals {
    @Override
    String say() {
        return "汪汪汪";
    }
}


