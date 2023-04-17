package creative.builder;

import com.google.common.base.Preconditions;

/**
 * 普通建造者模式
 * @author kangqing
 * @since 2023/4/11 07:07
 */
public class Cat {
     private int age;
     private String color;
     private String name;

     public String toString() {
         return "名字 = " + name + " 年龄 = " + age + " 颜色 = " + color;
     }

    /**
     * 私有化构造器，仅能通过建造者模式创建对象
     * 不添加setter方法，创建的是一个不可变对象
     * @param builder
     */
    private Cat(Builder builder) {
         this.age = builder.age;
         this.color = builder.color;
         this.name = builder.name;
     }

     public static class Builder {
        private String name;
        private int age = 1;
        private String color = "白色";

        public Cat build() {
            // 校验逻辑放在这里
            Preconditions.checkNotNull(color);
            Preconditions.checkArgument(age > 0);
            Preconditions.checkNotNull(name);
            return new Cat(this);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public Builder setColor(String color) {
            this.color = color;
            return this;
        }
     }
}
