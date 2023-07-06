package creative.builder.pc.pojo;

import creative.builder.pc.IPCSelector;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:32
 */
public class LJMouse implements IPCSelector {
    @Override
    public String name() {
        return "鼠标";
    }

    @Override
    public String brand() {
        return "罗技";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("87");
    }

    @Override
    public String desc() {
        return "罗技鼠标，支持各种办公、游戏场景，1年之内只换不修";
    }
}
