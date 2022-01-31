import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class WeatherClient {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String city = new StringReceiver().receive(WeatherMessage.CITY.getMsg());
        String apiKey = ResourcesReader.readText("weatherClient/apiKey.txt");
        String request = "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid="
                + apiKey
                + "&units=metric&lang=ru";
        System.out.println(request);
        String json = getJson(request);
        JsonWeatherParser parser = new JsonWeatherParser(city, json);
        parser.parseWeather();
        System.out.println(parser);
    }

    private static String getJson(String request) throws URISyntaxException, IOException {
        URI uri = new URI(request);
        URL url = uri.toURL();
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        Scanner scanner = new Scanner(urlConnection.getInputStream());
        return scanner.nextLine();
    }

}
