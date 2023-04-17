package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 棋子工厂，提供 32 个共享的棋子
 * @author kangqing
 * @since 2023/4/17 21:04
 */
public class ChessPieceUnitFactory {

     private static final Map<Integer, ChessPieceUnit> piece = new HashMap<>();

     static {
         piece.put(1, new ChessPieceUnit(1, "将", ChessPieceUnit.Color.BLACK));
         piece.put(17, new ChessPieceUnit(17, "帅", ChessPieceUnit.Color.RED));
         // 省略其余30颗棋子
     }
     // 获取共享的棋子
     public static ChessPieceUnit getChessUnit(int chessPieceId) {
         return piece.get(chessPieceId);
     }
}
