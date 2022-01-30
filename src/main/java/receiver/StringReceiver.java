import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReceiver {

    public String receive(String conditions) {
        String temp = "";
        while (temp.length() == 0) {
            System.out.println(conditions);
            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))){
                temp = inputReader.readLine();
            } catch (IOException e) {
                System.out.println(ReceiverMessage.IO_ERROR.getMsg());
                continue;
            } catch (Exception ex) {
                System.out.println(ReceiverMessage.ERROR.getMsg());
                continue;
            }
        }
        return temp;
    }
}
