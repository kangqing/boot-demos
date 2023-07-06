package creative.builder.aaa.matter;

import creative.builder.aaa.Matter;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/5 21:27
 */
public class DongPengTile implements Matter {
    public String scene() {
        return "地砖";
    }
    public String brand() {
        return "东鹏瓷砖";
    }
    public String model() {
        return "10001";
    }
    public BigDecimal price() {
        return new BigDecimal(102);
    }
    public String desc() {
        return "东鹏瓷砖以品质铸就品牌，科技推动品牌，⼝碑传播品牌为宗旨，2014年品牌价值132.35亿元，位列建陶⾏业榜⾸。";
    }
}
