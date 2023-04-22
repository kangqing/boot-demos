package behavioral.statemachine;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kangqing
 * @since 2023/4/23 07:09
 */
public class MarioStateMachine {

    @Getter
    @Setter
    private int score;

    @Setter
    private IMario currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    public State getCurrentState() {
        return this.currentState.getName();
    }


    // 重要，状态机转换
    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }

    public void obtainCaps() {
        this.currentState.obtainCaps(this);
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }

    public void meetMonster() {
        this.currentState.meetMonster(this);
    }

}
