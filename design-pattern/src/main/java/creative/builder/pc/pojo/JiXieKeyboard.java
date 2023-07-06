package creative.builder.pc.pojo;

import creative.builder.pc.IPCSelector;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:24
 */
public class JiXieKeyboard implements IPCSelector {
    @Override
    public String name() {
        return "键盘";
    }

    @Override
    public String brand() {
        return "罗技-机械";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("609");
    }

    @Override
    public String desc() {
        return "罗技机械键盘，专门为你定制的高端游戏键盘";
    }
}
