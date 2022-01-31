import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JsonWeatherParser {

    private final String city;
    private final String json;
    private String report;
    private int realTemperature;
    private int feelTemperature;
    private int speedWind;
    private int gustWind;


    public void parseWeather() {
        parseTemperature();
        parseWind();
        writeReport();
    }

    private void parseTemperature() {
        List<Double> temperature = JsonPath.read(json, "$..main[*]");
        realTemperature = (int) Math.round(temperature.get(0));
        try{
            feelTemperature = (int) Math.round(temperature.get(1));
        }catch(Exception e) {
            System.out.println(WeatherMessage.WEATHER_ERROR.getMsg());
            feelTemperature = realTemperature;
        }
    }

    private void parseWind() {
        List<Double> speed = JsonPath.read(json, "$..wind.speed");
        speedWind = (int) Math.round(speed.get(0));
        try {
            List<Double> gust = JsonPath.read(json, "$..wind.gust");
            gustWind = (int) Math.round(gust.get(0));
        } catch(Exception e) {
            System.out.println(WeatherMessage.WEATHER_ERROR.getMsg());
            gustWind = speedWind;
        }
    }

    private void writeReport() {
        StringBuilder sb = new StringBuilder("Погода в населённом пункте ");
        sb.append(city);
        sb.append("\nТемпература воздуха: ");
        sb.append(realTemperature);
        sb.append("\u00b0C");
        if (feelTemperature != realTemperature) {
            sb.append(", ощущается как ");
            sb.append(feelTemperature);
            sb.append("\u00b0C");
        }
        sb.append("\nСкорость ветра: ");
        sb.append(speedWind);
        sb.append(" м/с");
        if (gustWind != speedWind) {
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
