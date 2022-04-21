package main.java.exchangeRate;

public enum RateMessage {

    ENTER_CODE("Введите трёхбуквенный код интересующей вас валюты"),
    ERROR_CODE("Такой код не найден"),
    ERROR_IO("Ошибка соединения с сервером"),
    EXIT_APP("Выйти из программы? ('y', 'n')");

    private String msg;

    RateMessage(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
}
