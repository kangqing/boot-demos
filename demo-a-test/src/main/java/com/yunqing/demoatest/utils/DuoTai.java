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

        Xingzhuang x = new Changfang();
        System.out.println(x.area(2, 5));
        x = new Sanjiao();
        System.out.println(x.area(2, 5));
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


//--------------------多态的另一种 实现接口 并重写接口中的方法
//定义形状接口
interface Xingzhuang {
    default Integer area(int width, int height) {
        return 0;
    }
}

class Sanjiao implements Xingzhuang {

    @Override
    public Integer area(int width, int height) {
        return width * height / 2;
    }
}

class Changfang implements Xingzhuang {

    @Override
    public Integer area(int width, int height) {
        return width * height;
    }
}


