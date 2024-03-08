package creative.prototype.exam;

import creative.prototype.exam.util.QuestionBankController;
import lombok.SneakyThrows;

/**
 * @author kangqing
 * @since 2024/3/8 17:15
 */
public class ApiTest {
    @SneakyThrows
    public static void main(String[] args) {
        QuestionBankController questionBankController = new QuestionBankController();
        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));
    }
}
