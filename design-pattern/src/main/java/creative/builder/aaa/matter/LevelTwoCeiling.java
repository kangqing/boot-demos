package creative.builder.aaa.matter;

import creative.builder.aaa.Matter;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/5 21:23
 */
public class LevelTwoCeiling implements Matter {
    public String scene() {
        return "吊顶";
    }
    public String brand() {
        return "装修公司⾃带";
    }
    public String model() {
        return "⼆级顶";
    }
    public BigDecimal price() {
        return new BigDecimal(850);
    }

    public String desc() {
        return "两个层次的吊顶，⼆级吊顶⾼度⼀般就往下吊20cm，要是层⾼很⾼，也可增加每级的厚度";
    }
}
