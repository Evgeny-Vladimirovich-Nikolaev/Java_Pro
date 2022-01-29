public enum WeatherMessage {

    CITY("Введите название населённого пункта");

    private String msg;

    WeatherMessage(String msg) {
        this.msg = msg;
    }

    String getMsg() {
        return msg;
    }
}
