public enum WeatherMessage {

    CITY("Введите название населённого пункта"),
    WEATHER_ERROR("Данные о погоде неполные"),
    ERROR("""
            Не удалось получить информацию о погоде для\040
            %s.\040
            Проверьте правильность написания запроса\040
            или попробуйте подключиться позже.\040
            """);

    private String msg;

    WeatherMessage(String msg) {
        this.msg = msg;
    }

    String getMsg() {
        return msg;
    }
}
