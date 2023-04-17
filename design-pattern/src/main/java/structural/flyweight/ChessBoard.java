package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 棋盘类
 * 所有棋盘中的棋子都共享32个享元对象
 * @author kangqing
 * @since 2023/4/17 21:10
 */
public class ChessBoard {

    private Map<Integer, ChessPiece> map = new HashMap<>();
    // 初始化棋盘
    public ChessBoard() {
        init();
    }

    private void init() {
        map.put(1, new ChessPiece(ChessPieceUnitFactory.getChessUnit(1), 0, 0));
        map.put(17, new ChessPiece(ChessPieceUnitFactory.getChessUnit(17), 0, 0));
        // 省略其他棋子的初始化位置
    }

    public void move(int chessPieceId, int toPositionX, int toPositionY) {
        // 省略代码实现
        map.put(chessPieceId, new ChessPiece(ChessPieceUnitFactory.getChessUnit(chessPieceId), toPositionX, toPositionY));
    }
}
