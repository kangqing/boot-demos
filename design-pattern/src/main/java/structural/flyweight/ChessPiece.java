package structural.flyweight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 棋子类
 * @author kangqing
 * @since 2023/4/17 21:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChessPiece {
     // 棋子使用享元类对象
     private ChessPieceUnit chessPieceUnit;
     private int positionX;
     private int positionY;
}
