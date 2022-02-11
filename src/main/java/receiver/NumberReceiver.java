package receiver;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
public class NumberReceiver  extends Thread implements Callable<Integer> {

    private static final String INVALID_VALUE = "%s  не является допустимым значением";
    private static final String ERROR = "Неизвестная ошибка";
    private static final String IO_ERROR = "Ошибка ввода/вывода";
    private final String conditions;
    private final int min, max;


    public int receive() {
        String temp = "";
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.println(conditions);
            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))){
                temp = inputReader.readLine();
                choice = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                System.out.printf(INVALID_VALUE, temp);
            }
            catch (IOException e) {
                System.out.println(IO_ERROR);
            } catch (Exception ex) {
                System.out.println(ERROR);
            }
        }
        return choice;
    }

    @Override
    public Integer call() throws Exception {
        return receive();
    }
}
