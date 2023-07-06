package creative.builder.pc.pojo;

import creative.builder.pc.IPCSelector;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:36
 */
public class LXSound implements IPCSelector {
    @Override
    public String name() {
        return "音响";
    }

    @Override
    public String brand() {
        return "联想";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("80");
    }

    @Override
    public String desc() {
        return "联想音响，高音质体验";
    }
}
