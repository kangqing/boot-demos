package creative.builder.pc.pojo;

import creative.builder.pc.IPCSelector;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:35
 */
public class SNSound implements IPCSelector {
    @Override
    public String name() {
        return "音响";
    }

    @Override
    public String brand() {
        return "索尼";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("390");
    }

    @Override
    public String desc() {
        return "索尼音响，音质保障，让耳朵怀孕的声音体验";
    }
}
