package creative.builder.aaa.matter;

import creative.builder.aaa.Matter;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/5 21:22
 */
public class LevelOneCeiling implements Matter {
    public String scene() {
        return "吊顶";
    }
    public String brand() {
        return "装修公司⾃带";
    }
    public String model() {
        return "⼀级顶";
    }
    public BigDecimal price() {
        return new BigDecimal(260);
    }
    public String desc() {
        return "造型只做低⼀级，只有⼀个层次的吊顶，⼀般离顶120-150mm";
    }
}
