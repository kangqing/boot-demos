package builder;

import com.google.common.base.Preconditions;
import lombok.Builder;

/**
 * 使用lombok建造者模式怎么做检验
 * @author kangqing
 * @since 2023/4/11 07:39
 */
@Builder
public class LombokCat {
    private int age;
    private String color;
    private String name;

    public String toString() {
        return "名字 = " + name + " 年龄 = " + age + " 颜色 = " + color;
    }

    private void valid() {
        // 校验逻辑放在这里
        Preconditions.checkNotNull(color);
        Preconditions.checkArgument(age > 0);
        Preconditions.checkNotNull(name);
    }

    /**
     * 自定义builder 继承自lombok生产的builder
     */
    public static class CustomBuilder extends LombokCatBuilder {
        public CustomBuilder() {
            super();
        }
        // 重写build方法，在build执行之前校验
        @Override
        public LombokCat build() {
            LombokCat build = super.build();
            build.valid();
            return build;
        }

        public static LombokCatBuilder builder() {
            return new CustomBuilder();
        }
    }
}
