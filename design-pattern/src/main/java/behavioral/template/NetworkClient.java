package behavioral.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 客户端
 * @author kangqing
 * @since 2023/4/19 07:20
 */
public class NetworkClient {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        System.out.print("输入 username: ");
        String userName = reader.readLine();

        // Enter the message.
        System.out.print("输入 message: ");
        String message = reader.readLine();

        System.out.println("\nChoose social network for posting message.\n" +
                "1 - WeChat\n" +
                "2 - Tiktok");
        int choice = Integer.parseInt(reader.readLine());

        // Create proper network object and send the message.
        if (choice == 1) {
            network = new WeChat();
        } else if (choice == 2) {
            network = new Tiktok();
        }
        network.send(userName, message);
    }
}
