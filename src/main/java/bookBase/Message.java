package bookBase;

public enum Message {

    SELECT_REQUEST("""
            Выберите категорию поиска:
            1 - по названию
            2 - по автору
            3 - по цене
            0 - выйти
            """),

    EXIT_APP("Выйти из программы ('y', 'n')");

    private String msg;

    Message(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
}
