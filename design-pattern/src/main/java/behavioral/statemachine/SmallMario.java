package behavioral.statemachine;

/**
 * 小马里奥,省略了超级马里奥和火焰马里奥，和斗篷马里奥的实现
 * @author kangqing
 * @since 2023/4/23 07:05
 */
public class SmallMario implements IMario{

    public static final SmallMario instance = new SmallMario();

    private SmallMario() {}

    public static SmallMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine stateMachine) {
        stateMachine.setCurrentState(SuperMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() + 100);
    }

    @Override
    public void obtainCaps(MarioStateMachine stateMachine) {
        stateMachine.setCurrentState(CapeMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() + 200);
    }
    @Override
    public void obtainFireFlower(MarioStateMachine stateMachine) {
        stateMachine.setCurrentState(FireMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster(MarioStateMachine stateMachine) {
        System.out.println("GAME OVER");
    }


}
