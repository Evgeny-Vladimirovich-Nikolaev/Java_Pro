import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JsonWeatherParser {

    private final String city;
    private final String json;
    private String report;
    private Integer realTemperature;
    private Integer feelTemperature;
    private Integer speedWind;
    private Integer gustWind;


    public void parseWeather() {
        parseTemperature();
        parseWind();
        writeReport();
    }

    private void parseTemperature() {
        try{
            List<Double> realTemp = JsonPath.read(json, "$..main.temp");
            realTemperature = (int) Math.round(realTemp.get(0));
            List<Double> feelTemp = JsonPath.read(json, "$..main.feels_like");
            feelTemperature = (int) Math.round(feelTemp.get(0));
        }catch(Exception e) {
            System.out.println(WeatherMessage.WEATHER_ERROR.getMsg());
        }
    }

    private void parseWind() {
        try {
            List<Double> speed = JsonPath.read(json, "$..wind.speed");
            speedWind = (int) Math.round(speed.get(0));
            List<Double> gust = JsonPath.read(json, "$..wind.gust");
            gustWind = (int) Math.round(gust.get(0));
        } catch(Exception e) {
            System.out.println(WeatherMessage.WEATHER_ERROR.getMsg());
        }
    }

    private void writeReport() {
        StringBuilder sb = new StringBuilder("Погода в населённом пункте ");
        sb.append(city);
        if (realTemperature != null) {
            sb.append("\nТемпература воздуха: ");
            sb.append(realTemperature);
            sb.append("\u00b0C");
        }
        if (feelTemperature != null) {
            sb.append(", ощущается как ");
            sb.append(feelTemperature);
            sb.append("\u00b0C");
        }
        if (speedWind != null) {
            sb.append("\nСкорость ветра: ");
            sb.append(speedWind);
            sb.append(" м/с");
        }
        if (gustWind != null) {
            sb.append(" с порывами до ");
            sb.append(gustWind);
            sb.append(" м/с");
        }
        report = sb.toString();
    }

    @Override
    public String toString() {
        return report;
    }
}
