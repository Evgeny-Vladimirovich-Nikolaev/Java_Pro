package main.java.receiver;

import lombok.experimental.UtilityClass;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@UtilityClass
public class ValueReceiver {

    private static final String ERROR ="Неизвестная ошибка";
    private static final String IO_ERROR = "Ошибка ввода/вывода";
    private static final String INVALID_VALUE = "%s не является допустимым значением";
    private static final BufferedReader inputReader = new BufferedReader
            (new InputStreamReader(System.in));

    public static String receiveString(String conditions) {
        String temp = "";
        while (temp.length() == 0) {
            System.out.println(conditions);
            try {
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
    public static int receiveInt(String conditions) {
        String temp = "";
        int value = 0;
        while (true) {
            System.out.println(conditions);
            try {
                temp = inputReader.readLine();
                value = Integer.parseInt(temp);
                break;
            } catch (NumberFormatException e) {
                System.out.printf(INVALID_VALUE, temp);
                break;
            } catch(IOException e) {
                System.out.println(IO_ERROR);
                break;
            } catch (Exception ex) {
                System.out.println(ERROR);
                break;
            }
        }
        return value;
    }

    public static void closeReader() {
        try {
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
