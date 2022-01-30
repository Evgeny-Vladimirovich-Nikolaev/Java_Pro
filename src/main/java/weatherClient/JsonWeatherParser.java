package weatherClient;

import com.jayway.jsonpath.JsonPath;
import java.util.List;

public class JsonWeatherParser {

    private String city;
    List<Double> temperature;
    List<Double>  windSpeed;
    List<Double>  windGust;
    List<Integer> windDirection;

    public JsonWeatherParser(String city, String json) {
        this.city = city;
        temperature = JsonPath.read(json, "$..main[*]");
        windSpeed = JsonPath.read(json, "$..wind.speed");
        windGust = JsonPath.read(json, "$..wind.gust");
        windDirection = JsonPath.read(json, "$..wind.deg");
    }

    @Override
    public String toString() {
        StringBuilder report = new StringBuilder("Погода в населённом пункте ");
        report.append(city);
        report.append("\nТемпература воздуха: ");
        report.append(temperature.get(0));
        report.append("\u00b0C");
        report.append(", ощущается как ");
        report.append(temperature.get(1));
        report.append("\u00b0C");
        report.append("\nСкорость ветра: ");
        report.append(windSpeed.get(0));
        report.append(" м/с");
        if(windGust.size() != 0){
            report.append(" с порывами до ");
            report.append(windGust.get(0));
            report.append(" м/с");
        }
        return report.toString();
    }
}
