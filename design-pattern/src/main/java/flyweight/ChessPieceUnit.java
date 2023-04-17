package flyweight;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 享元模式：享元类
 * 模拟一个百万级游戏厅中的象棋，每个房间一个棋盘，棋盘上有32个象棋棋子，如果每个棋子都用一个类标识id,text,color,positionX,positionY
 * 则需要 32万个棋子类，因为每一盘棋局只有32个棋子，只是每局位置不同，所以可以利用享元模式共享32个棋子，节约内存
 * @author kangqing
 * @since 2023/4/17 20:57
 */
@Data
@AllArgsConstructor
public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;


    public static enum Color {
        RED, BLACK
    }
}


