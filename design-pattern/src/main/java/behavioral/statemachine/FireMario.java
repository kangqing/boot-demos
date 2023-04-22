package behavioral.statemachine;

/**
 * @author kangqing
 * @since 2023/4/23 07:24
 */
public class FireMario implements IMario{

    public static final FireMario instance = new FireMario();

    private FireMario() {}

    public static FireMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.FIRE;
    }
}
