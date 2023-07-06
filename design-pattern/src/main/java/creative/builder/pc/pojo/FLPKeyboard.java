package creative.builder.pc.pojo;

import creative.builder.pc.IPCSelector;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:27
 */
public class FLPKeyboard implements IPCSelector {
    @Override
    public String name() {
        return "键盘";
    }

    @Override
    public String brand() {
        return "飞利浦";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("24");
    }

    @Override
    public String desc() {
        return "飞利浦高端商务办公键盘";
    }
}
