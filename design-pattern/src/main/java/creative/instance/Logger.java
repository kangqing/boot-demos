package creative.instance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author kangqing
 * @since 2023/4/7 21:18
 */
public class Logger {

    private FileWriter writer;

    public Logger() {
        File file = new File(System.getProperty("user.dir") + "/log.txt");
        try {
            writer = new FileWriter(file, true); // true表示追加写入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
