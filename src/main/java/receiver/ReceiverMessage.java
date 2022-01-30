enum ReceiverMessage {

    ERROR("Неизвестная ошибка"),
    IO_ERROR("Ошибка ввода/вывода");

    private String msg;

    ReceiverMessage(String msg) {
        this.msg = msg;
    }

    String getMsg() {
        return msg;
    }
}
