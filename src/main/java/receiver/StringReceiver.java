package receiver;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
public class StringReceiver  extends Thread implements Callable<String> {

    private static final String ERROR ="Неизвестная ошибка";
    private static final String IO_ERROR = "Ошибка ввода/вывода";
    private final String conditions;

    public String receive() {
        String temp = "";
        while (temp.length() == 0) {
            System.out.println(conditions);
            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))){
                temp = inputReader.readLine();
            } catch (IOException e) {
                System.out.println(IO_ERROR);
                continue;
            } catch (Exception ex) {
                System.out.println(ERROR);
                continue;
            }
        }
        return temp;
    }

    @Override
    public String call() throws Exception {
        return receive();
    }
}
