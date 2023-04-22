package behavioral.statemachine;

import lombok.Getter;

/**
 * 马里奥接口
 * @author kangqing
 * @since 2023/4/23 06:59
 */
public interface IMario {
    State getName();
    // 吃了普通蘑菇
    default void obtainMushRoom(MarioStateMachine stateMachine) {

    }
    // 吃了斗篷蘑菇
    default void obtainCaps(MarioStateMachine stateMachine) {

    }
    // 吃了火焰蘑菇
    default void obtainFireFlower(MarioStateMachine stateMachine) {

    }
    // 遇到怪物
    default void meetMonster(MarioStateMachine stateMachine) {

    }
}

enum State {
    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPS(3);

    @Getter
    private int value;

    private State(int value) {
        this.value = value;
    }

}
