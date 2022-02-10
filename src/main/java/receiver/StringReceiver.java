package receiver;

import lombok.experimental.UtilityClass;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@UtilityClass
public class StringReceiver {

    private static final String ERROR ="Неизвестная ошибка";
    private static final String IO_ERROR = "Ошибка ввода/вывода";

    public static String receive(String conditions) {
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
}
