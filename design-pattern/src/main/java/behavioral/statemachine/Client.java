package behavioral.statemachine;

/**
 * @author kangqing
 * @since 2023/4/23 07:27
 */
public class Client {

    public static void main(String[] args) {
        final MarioStateMachine marioStateMachine = new MarioStateMachine();
        marioStateMachine.obtainMushRoom();

        final int score = marioStateMachine.getScore();
        final State currentState = marioStateMachine.getCurrentState();

        System.out.println("当前状态是：" + currentState + ", 当前分数 = " + score);
    }
}
