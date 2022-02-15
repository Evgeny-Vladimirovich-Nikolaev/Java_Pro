package weatherClient;

import receiver.ValueReceiver;
import utils.ResourcesReader;


import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class WeatherClient {

    public static void main(String[] args) throws Exception {
        String city = ValueReceiver.receiveString(WeatherMessage.CITY.getMsg());
        String apiKey = ResourcesReader.readText("/weatherClient/apiKey.txt");
        String request = "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid="
                + apiKey
                + "&units=metric&lang=ru";
        System.out.println(request);
        String json = getJson(request);
        if(json != null) {
            JsonWeatherParser parser = new JsonWeatherParser(city, json);
            parser.parseWeather();
            System.out.println(parser);

        }
    }

    private static String getJson(String request)  {
        try {
            URI uri = new URI(request);
            URL url = uri.toURL();
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            Scanner scanner = new Scanner(urlConnection.getInputStream());
            return scanner.nextLine();
        } catch (URISyntaxException e) {
            System.out.println("Не удалось обработать запрос");
        } catch (IOException e) {
            System.out.println("Ошибка соединения");
        }
        return null;
    }

}
