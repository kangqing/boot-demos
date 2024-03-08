package creative.prototype.exam.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kangqing
 * @since 2024/3/8 17:02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerQuestion {
    private String name; // 题目
    private String key; // 答案
}
