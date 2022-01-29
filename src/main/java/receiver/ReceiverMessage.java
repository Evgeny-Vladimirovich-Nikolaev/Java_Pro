enum ReceiverMessage {

    ERROR("Неизвестная ошибка"),
    INVALID_LINE(" не является числом"),
    INVALID_NUMBER(" - неправильный формат числа"),
    IO_ERROR("Ошибка ввода/вывода");

    private String msg;

    ReceiverMessage(String msg) {
        this.msg = msg;
    }

    String getMsg() {
        return msg;
    }
}
