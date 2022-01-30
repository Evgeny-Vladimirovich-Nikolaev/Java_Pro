import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class WeatherClient {


    public static void main(String[] args) throws URISyntaxException, IOException {

        String city = new StringReceiver().receive(WeatherMessage.CITY.getMsg());
        String apiKey = ResourcesReader.readText("weatherClient/apiKey.txt");

        String request = "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid="
                + apiKey
                + "&lang=ru";


        System.out.println(request);
        URI uri = new URI(request);

        URL url = uri.toURL();
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        Scanner scanner = new Scanner(urlConnection.getInputStream());
        String json = scanner.nextLine();
        System.out.println(json);
        writeJson(json);
    }

    private static void writeJson(String json) {
        try {
            Path jsonPath = Paths.get("./src/main/resources/weather.json");
            Files.writeString(jsonPath, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
