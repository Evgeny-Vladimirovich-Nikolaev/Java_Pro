package main.java.ormBookBase.controller;

public enum Message {


    SELECT_REQUEST(
            """
            Выберите категорию поиска:
            1 - по названию
            2 - по автору
            3 - по цене
            0 - выйти
            """),
    ENTER_TITLE(
            """
            Введите полное или частичное название книги
            (без кавычек, не более 255 символов)
            """),
    ENTER_NAME("Введите полное имя или часть имени автора"),
    ENTER_PRICE("Укажите максимальную стоимость товара"),
    EXIT_APP("Выйти из программы? ('y', 'n')"),
    NO_RESULTS("Ничего не найдено");

    private final String msg;

    Message(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
}
