package main.java.weatherClient;

public enum WeatherMessage {

    CITY("Введите название населённого пункта"),
    WIND_ERROR("Нет полных данных о скорости ветра"),
    TEMPERATURE_ERROR("Нет полных данных о температуре воздуха"),
    HUMIDITY_ERROR("Нет полных данных о влажности воздуха"),
    PRESSURE_ERROR("Нет полных данных об атмосферном давлении"),
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

    public String getMsg() {
        return msg;
    }
}
