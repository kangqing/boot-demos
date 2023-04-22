package behavioral.statemachine;

/**
 * @author kangqing
 * @since 2023/4/23 07:19
 */
public class SuperMario implements IMario{

    public static final SuperMario instance = new SuperMario();

    private SuperMario() {}

    public static SuperMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }
}
