package receiver;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@UtilityClass
public class NumberReceiver {

    private static final String INVALID_VALUE = "%s  не является допустимым значением";
    private static final String ERROR = "Неизвестная ошибка";
    private static final String IO_ERROR = "Ошибка ввода/вывода";


    public static int receive(String conditions, int min, int max) {
        String temp = "";
        int choice = -1;
        while (choice < 0 || choice >3) {
            System.out.println(conditions);
            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))){
                temp = inputReader.readLine();
                choice = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                System.out.printf(INVALID_VALUE, temp);
                continue;
            }
            catch (IOException e) {
                System.out.println(IO_ERROR);
                continue;
            } catch (Exception ex) {
                System.out.println(ERROR);
                continue;
            }
        }
        return choice;
    }

}
