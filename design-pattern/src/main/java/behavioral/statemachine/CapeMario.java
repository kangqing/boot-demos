package behavioral.statemachine;

/**
 * @author kangqing
 * @since 2023/4/23 07:24
 */
public class CapeMario implements IMario{

    public static final CapeMario instance = new CapeMario();

    private CapeMario() {}

    public static CapeMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.CAPS;
    }
}
