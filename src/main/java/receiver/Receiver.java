import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Receiver {

    public static final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public static void close() {
        try {
            inputReader.close();
        } catch (IOException e) {
            System.out.println(ReceiverMessage.IO_ERROR.getMsg());
        }
    }
}
